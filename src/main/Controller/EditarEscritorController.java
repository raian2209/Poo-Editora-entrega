package main.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Entities.Escritor; // <-- MUDANÇA
import main.Model.Service.EscritorService; // <-- MUDANÇA
import main.view.HelloApplication;

// A interface agora especifica o tipo Escritor
public class EditarEscritorController implements DataReceiver<Escritor> {

    @FXML private TextField nomeField;
    @FXML private TextField cpfField;
    @FXML private TextField enderecoField;
    @FXML private PasswordField senhaField;
    @FXML private Button btnSalvar;
    @FXML private Button btnClose;

    private Stage stage;
    private Escritor escritorParaEditar;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Recebe o objeto Escritor selecionado na tabela e preenche os campos.
     * @param escritor O objeto Escritor para editar.
     */
    @Override
    public void initData(Escritor escritor) { // <-- MUDANÇA
        this.escritorParaEditar = escritor; // <-- MUDANÇA
        nomeField.setText(escritor.getNome());
        cpfField.setText(escritor.getCpf());
        enderecoField.setText(escritor.getEndereco());
    }

    @FXML
    void handleSalvarAlteracoes(ActionEvent event) {
        if (nomeField.getText().isEmpty() || enderecoField.getText().isEmpty()) {
            mostrarAlerta("Campos Vazios", "O nome e o endereço não podem estar vazios.");
            return;
        }

        // Atualiza o objeto Escritor
        escritorParaEditar.setNome(nomeField.getText());
        escritorParaEditar.setEndereco(enderecoField.getText());

        if (!senhaField.getText().isEmpty()) {
            escritorParaEditar.setSenha(senhaField.getText());
        }

        // Usa o serviço de Escritor para salvar
        EscritorService escritorService = new EscritorService();
        escritorService.atualisar(escritorParaEditar);

        System.out.println("Escritor atualizado com sucesso: " + escritorParaEditar.getNome());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText("Dados foram editados com sucesso!");
        alert.showAndWait();

        HelloApplication.telaDono();

        if (stage != null) {
            stage.close();
        }
    }

    @FXML
    void handleClose(ActionEvent event) {
        // Volta para a tela de gerenciamento de Autores/Escritores
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