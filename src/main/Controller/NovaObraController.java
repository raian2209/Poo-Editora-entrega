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
import main.Model.Builders.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class NovaObraController implements Initializable {

    @FXML private TextField tituloField;
    @FXML private ComboBox<Escritor> autorComboBox;
    @FXML private ComboBox<String> generoComboBox;
    @FXML private Button btnAdicionar;
    @FXML private TextField AnoBox;
    @FXML private Button btnClose;
    private Stage stage; // Referência à janela (Stage) para poder fechá-la

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Carrega os dados para os ComboBox
        carregarAutores();
        carregarGeneros();
    }

    private void carregarAutores() {
        EscritorService escritorService = new EscritorService(); // Simulado
        List<Escritor> escritores = escritorService.BuscarTodos(); // Busca todos os autores no BD
        autorComboBox.setItems(FXCollections.observableArrayList(escritores));
    }

    private void carregarGeneros() {
        // Lista fixa de gêneros para o exemplo
        generoComboBox.setItems(FXCollections.observableArrayList(
                "Ficção", "Romance", "Fantasia", "Suspense", "Biografia", "Técnico"
        ));
    }

    @FXML
    void handleAdicionarObra(ActionEvent event) {
        try {
            // 1. Validações
            if (tituloField.getText().isEmpty() || autorComboBox.getValue() == null || generoComboBox.getValue() == null || AnoBox.getText().isEmpty()) {
                throw new CamposVaziosException("Por favor, preencha todos os campos para adicionar a obra.");
            }

            int ano;
            try {
                ano = Integer.parseInt(AnoBox.getText());
            } catch (NumberFormatException e) {
                throw new NumeroInvalidoExeption("O campo 'Ano' deve conter um número válido.");
            }

            String titulo = tituloField.getText();
            ObraService obraService = new ObraService();
            if (!obraService.buscarPorTitulo(titulo).isEmpty()) {
                throw new TituloDigitadoJaExistente("Já existe uma obra cadastrada com este título.");
            }

            // ---------------------Builders

            Escritor escritor = autorComboBox.getValue();
            String genero = generoComboBox.getValue();

            ObraBuilder builder = new ConcreteObraBuilder();
            ObraDirector director = new ObraDirector();

            // Verificar o genero da obra e fazer com q ela seja do genero selecionado
            switch (genero) {
                case "Fantasia":
                    director.constructFantasia(builder, titulo, escritor, ano);
                    break;
                case "Ficção":
                    director.constructFiccao(builder, titulo, escritor, ano);
                    break;
                case "Romance":
                    director.constructRomance(builder, titulo, escritor, ano);
                    break;
                case "Suspense":
                    director.constructSuspense(builder, titulo, escritor, ano);
                    break;
                case "Técnico":
                    director.constructTecnico(builder, titulo, escritor, ano);
                    break;
                case "Biografia":
                    director.constructBiografia(builder, titulo, escritor, ano);
                    break;
            }

            Obra novaObra = builder.getResult();

            // ----------------------------------------

            // Aelrta
            obraService.salvar(novaObra);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText(null);
            alert.setContentText("Obra adicionada com sucesso!");
            alert.showAndWait();

            HelloApplication.telaDonoObra();

        } catch (CamposVaziosException e) {
            mostrarAlerta("Campos Vazios", e.getMessage());
        } catch (NumeroInvalidoExeption e) {
            mostrarAlerta("Ano Inválido", e.getMessage());
        } catch (TituloDigitadoJaExistente e) {
            mostrarAlerta("Título já Existe", e.getMessage());
        }
    }

    @FXML
     void handleClose(ActionEvent event){
        HelloApplication.telaDonoObra();
    }
    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Atenção");
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}