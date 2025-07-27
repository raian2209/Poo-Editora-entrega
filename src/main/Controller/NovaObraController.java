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
        // Validação dos campos
        if (tituloField.getText().isEmpty() || autorComboBox.getValue() == null || generoComboBox.getValue() == null || AnoBox.getText().isEmpty()) {
            mostrarAlerta("Campos Vazios", "Por favor, preencha todos os campos para adicionar a obra.");
            return;
        }
        // Criação do objeto Obra
        Integer ano = Integer.parseInt(AnoBox.getText());
        String titulo = tituloField.getText();
        Escritor escritor = autorComboBox.getValue();
        String genero = generoComboBox.getValue();

        //  construtor
        Obra novaObra = new Obra(titulo, genero,ano,escritor); // Ajuste conforme sua entidade

        // Salva no banco de dados
        ObraService obraService = new ObraService();
        obraService.salvar(novaObra);

        System.out.println("Obra adicionada com sucesso: " + novaObra);

        // Fecha a janela modal
        if (stage != null) {
            stage.close();
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