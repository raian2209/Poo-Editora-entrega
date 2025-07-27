package main.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Entities.Avaliador;
import main.Model.Service.AvaliadorService; // Supondo que você tenha um serviço para Avaliador
import main.view.HelloApplication;

public class NovoAvaliadorController {

    // Campos do formulário de Avaliador
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
    void handleAdicionarAvaliador(ActionEvent event) {
        // Validação dos campos
        if (nomeField.getText().isEmpty() || cpfField.getText().isEmpty() || enderecoField.getText().isEmpty() || senhaField.getText().isEmpty()) {
            mostrarAlerta("Campos Vazios", "Por favor, preencha todos os campos para adicionar o avaliador.");
            return;
        }

        // Criação do objeto Avaliador
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String endereco = enderecoField.getText();
        String senha = senhaField.getText();

        Avaliador novoAvaliador = new Avaliador(nome, cpf, endereco, senha);

        // Salva no banco de dados através do serviço/DAO
        AvaliadorService avaliadorService = new AvaliadorService();
        avaliadorService.salvar(novoAvaliador);

        System.out.println("Avaliador adicionado com sucesso: " + novoAvaliador.getNome());

        if (stage != null) {
            stage.close();
        }
    }

    @FXML
    void handleClose(ActionEvent event) {
        HelloApplication.telaDonoAvaliador();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Atenção");
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}