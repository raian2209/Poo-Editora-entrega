package main.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Entities.Escritor;
import main.Model.Service.EscritorService;
import main.view.HelloApplication;

public class NovoEscritorController {

    // Campos do formulário de Escritor
    @FXML private TextField nomeField;
    @FXML private TextField cpfField;
    @FXML private TextField enderecoField;
    @FXML private PasswordField senhaField;

    // Botões de ação
    @FXML private Button btnAdicionar;
    @FXML private Button btnClose;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void handleAdicionarEscritor(ActionEvent event) {
        // Validação dos campos
        if (nomeField.getText().isEmpty() || cpfField.getText().isEmpty() || enderecoField.getText().isEmpty() || senhaField.getText().isEmpty()) {
            mostrarAlerta("Campos Vazios", "Por favor, preencha todos os campos para adicionar o escritor.");
            return;
        }

        // Criação do objeto Escritor
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String endereco = enderecoField.getText();
        String senha = senhaField.getText();

        Escritor novoEscritor = new Escritor(nome, cpf, endereco, senha);

        // Salva no banco de dados através do serviço/DAO
        EscritorService escritorService = new EscritorService();
        escritorService.salvar(novoEscritor);

        System.out.println("Escritor adicionado com sucesso: " + novoEscritor.getNome());

        if (stage != null) {
            stage.close();
        }
    }

    @FXML
    void handleClose(ActionEvent event) {
        HelloApplication.telaDono();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Atenção");
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}