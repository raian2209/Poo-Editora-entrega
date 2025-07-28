package main.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Entities.Escritor;
import main.Entities.Obra;
import main.Exceptions.CamposVaziosException;
import main.Exceptions.NumeroInvalidoExeption;
import main.Exceptions.TituloDigitadoJaExistente;
import main.Model.Service.EscritorService;
import main.Model.Service.ObraService;
import main.view.HelloApplication;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

// A interface agora especifica o tipo Obra
public class EditarObraController implements DataReceiver<Obra>, Initializable {

    @FXML private TextField tituloField;
    @FXML private TextField anoField;
    @FXML private ComboBox<Escritor> autorComboBox;
    @FXML private ComboBox<String> generoComboBox;
    @FXML private Button btnSalvar;
    @FXML private Button btnClose;

    private Stage stage;
    private Obra obraParaEditar; // Armazena o objeto que estamos editando

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Carrega os dados para os ComboBox antes de qualquer outra coisa
        carregarAutores();
        carregarGeneros();
    }

    /**
     * Recebe o objeto Obra selecionado na tabela e preenche os campos.
     * @param obra O objeto Obra para editar.
     */
    @Override
    public void initData(Obra obra) {
        this.obraParaEditar = obra;
        tituloField.setText(obra.getTitulo());
        anoField.setText(String.valueOf(obra.getAno()));

        // Seleciona o autor e o gênero correspondentes nos ComboBox
        autorComboBox.setValue(obra.getAutor());
        generoComboBox.setValue(obra.getGenero());
    }

    @FXML
    void handleSalvarAlteracoes(ActionEvent event) {
        try {

            if (tituloField.getText().isEmpty() || anoField.getText().isEmpty() || autorComboBox.getValue() == null || generoComboBox.getValue() == null) {
                throw new CamposVaziosException("Todos os campos devem ser preenchidos.");
            }

            int ano;
            try {
                ano = Integer.parseInt(anoField.getText());
            } catch (NumberFormatException e) {
                throw new NumeroInvalidoExeption("O campo 'Ano' deve conter um número válido.");
            }

            String novoTitulo = tituloField.getText();
            ObraService obraService = new ObraService();

            if (!novoTitulo.equals(obraParaEditar.getTitulo()) && !obraService.buscarPorTitulo(novoTitulo).isEmpty()) {
                throw new TituloDigitadoJaExistente("Já existe outra obra cadastrada com este título.");
            }

            obraParaEditar.setTitulo(novoTitulo);
            obraParaEditar.setAno(ano);
            obraParaEditar.setAutor(autorComboBox.getValue());
            obraParaEditar.setGenero(generoComboBox.getValue());

            obraService.atualisar(obraParaEditar);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText(null);
            alert.setContentText("Obra atualizada com sucesso!");
            alert.showAndWait();

            HelloApplication.telaDonoObra();

            if (stage != null) {
                stage.close();
            }

        } catch (CamposVaziosException e) {
            mostrarAlerta("Campos Vazios", e.getMessage());
        } catch (NumeroInvalidoExeption e) {
            mostrarAlerta("Ano Inválido", e.getMessage());
        } catch (TituloDigitadoJaExistente e) {
            mostrarAlerta("Título já Existe", e.getMessage());
        }
    }

    @FXML
    void handleClose(ActionEvent event) {
        // Volta para a tela de gerenciamento de Obras
        HelloApplication.telaDonoObra();
    }

    // Métodos para popular os ComboBox
    private void carregarAutores() {
        EscritorService escritorService = new EscritorService();
        List<Escritor> escritores = escritorService.BuscarTodos();
        autorComboBox.setItems(FXCollections.observableArrayList(escritores));
    }

    private void carregarGeneros() {
        generoComboBox.setItems(FXCollections.observableArrayList(
                "Ficção", "Romance", "Fantasia", "Suspense", "Biografia", "Técnico"
        ));
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Atenção");
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}