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
        if (tituloField.getText().isEmpty() || anoField.getText().isEmpty() || autorComboBox.getValue() == null || generoComboBox.getValue() == null) {
            mostrarAlerta("Campos Vazios", "Todos os campos devem ser preenchidos.");
            return;
        }

        // Atualiza o objeto Obra com os novos dados
        obraParaEditar.setTitulo(tituloField.getText());
        obraParaEditar.setAno(Integer.parseInt(anoField.getText()));
        obraParaEditar.setAutor(autorComboBox.getValue());
        obraParaEditar.setGenero(generoComboBox.getValue());

        // Usa o serviço de Obra para salvar as alterações
        ObraService obraService = new ObraService();
        obraService.atualisar(obraParaEditar);

        System.out.println("Obra atualizada com sucesso: " + obraParaEditar.getTitulo());

        if (stage != null) {
            stage.close();
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