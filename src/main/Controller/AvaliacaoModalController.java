package main.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import main.Entities.Avaliacoes;
import main.Entities.Avaliador;
import main.Entities.Status;
import main.Model.Service.AvaliacaoService; // Serviço hipotético
import main.view.HelloApplication;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AvaliacaoModalController implements Initializable {


    @FXML private Label tituloLabel;
    @FXML private Label autorLabel;


    @FXML private ComboBox<Status> statusComboBox;
    @FXML private TextArea parecerTextArea;


    @FXML private Button btnSalvar;
    @FXML private Button btnClose;

    private Stage stage;
    private Avaliacoes avaliacaoParaEditar;
    private Avaliador avaliadorLogado;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Popula o ComboBox com as opções que o avaliador pode escolher
        statusComboBox.setItems(FXCollections.observableArrayList(
                Status.ACEITO,
                Status.REJEITADO
        ));
    }


    public void initData(Avaliacoes avaliacao, Avaliador avaliador) {
        this.avaliacaoParaEditar = avaliacao;
        this.avaliadorLogado = avaliador;
        // Preenche os labels com os dados da obra
        tituloLabel.setText(avaliacao.getObraAvaliar().getTitulo());
        autorLabel.setText(avaliacao.getObraAvaliar().getAutor().getNome());
    }

    @FXML
    void handleSalvarAvaliacao(ActionEvent event) {
        // Validação dos campos
        if (statusComboBox.getValue() == null || parecerTextArea.getText().isBlank()) {
            mostrarAlerta("Campos Incompletos", "Por favor, selecione um parecer e escreva uma justificativa.");
            return;
        }

        // Atualiza o objeto Avaliacoes
        avaliacaoParaEditar.setStatus(statusComboBox.getValue());
        avaliacaoParaEditar.setDataAvaliacao(LocalDateTime.now().toString()); // Atualiza a data para o momento atual


        // ex: avaliacaoParaEditar.setParecer(parecerTextArea.getText());
        avaliacaoParaEditar.setJustificativa(parecerTextArea.getText());

        AvaliacaoService avaliacaoService = new AvaliacaoService();
        avaliacaoService.atualisar(avaliacaoParaEditar);

        System.out.println("Avaliação salva com sucesso para a obra: " + avaliacaoParaEditar.getObraAvaliar().getTitulo());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText("Avaliação salva com sucesso!");
        alert.showAndWait();

        HelloApplication.telaAvaliadorLogin(avaliadorLogado);

        // Fecha o modal
        if (stage != null) {
            stage.close();
        }
    }

    @FXML
    void handleClose(ActionEvent event) {
        HelloApplication.telaAvaliadorLogin(avaliadorLogado);
    }

    private void mostrarAlerta(String titulo, String mensagem) {

    }
}