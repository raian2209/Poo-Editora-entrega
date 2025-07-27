package main.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Entities.Avaliador;
import main.Model.Service.AvaliadorService;
import main.view.HelloApplication;

public class EditarAvaliadorController implements DataReceiver<Avaliador>{

    @FXML private TextField nomeField;
    @FXML private TextField cpfField;
    @FXML private TextField enderecoField;
    @FXML private PasswordField senhaField;
    @FXML private Button btnSalvar;
    @FXML private Button btnClose;

    private Stage stage;
    private Avaliador avaliadorParaEditar; // Armazena o objeto que estamos editando

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Este é o método chave para passar o objeto da tela principal para esta tela.
     * Ele preenche os campos do formulário com os dados do avaliador.
     * @param avaliador O objeto Avaliador selecionado na tabela.
     */
    @Override
    public void initData(Avaliador avaliador) {
        this.avaliadorParaEditar = avaliador;
        nomeField.setText(avaliador.getNome());
        cpfField.setText(avaliador.getCpf());
        enderecoField.setText(avaliador.getEndereco());
        // O campo de senha começa vazio por segurança
    }

    @FXML
    void handleSalvarAlteracoes(ActionEvent event) {
        if (nomeField.getText().isEmpty() || enderecoField.getText().isEmpty()) {
            mostrarAlerta("Campos Vazios", "O nome e o endereço não podem estar vazios.");
            return;
        }

        avaliadorParaEditar.setNome(nomeField.getText());
        avaliadorParaEditar.setEndereco(enderecoField.getText());

        if (!senhaField.getText().isEmpty()) {
            avaliadorParaEditar.setSenha(senhaField.getText());
        }

        AvaliadorService avaliadorService = new AvaliadorService();
        avaliadorService.atualisar(avaliadorParaEditar);

        System.out.println("Avaliador atualizado com sucesso: " + avaliadorParaEditar.getNome());

        if (stage != null) {
            stage.close();
        }
    }

    @FXML
    void handleClose(ActionEvent event) {
        HelloApplication.telaDonoAvaliador();
    }

    // Método auxiliar para mostrar alertas
    private void mostrarAlerta(String titulo, String mensagem) { /* ... */ }
}