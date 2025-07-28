package main.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Entities.Avaliador;
import main.Exceptions.CPFInvalidoException;
import main.Exceptions.CPFJaExistenteException;
import main.Exceptions.CamposVaziosException;
import main.Model.Service.AvaliadorService; // Supondo que você tenha um serviço para Avaliador
import main.view.HelloApplication;
import main.Entities.Escritor;
import main.Model.Service.EscritorService;

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

        try {
            // Validação dos campos
            if (nomeField.getText().isEmpty() || cpfField.getText().isEmpty() || enderecoField.getText().isEmpty() || senhaField.getText().isEmpty()) {
                throw new CPFInvalidoException("Por favor, preencha todos os campos para adicionar o avaliador.");
            }

            //-----------------------------------------------------------------------------------
            // Verificação de CPF(E verificado tbm se já existem Escritores com o CPF passado)
            String cpf = cpfField.getText();
            EscritorService escritorService = new EscritorService();
            AvaliadorService avaliadorService = new AvaliadorService();

            // verificação se o CPF JÁ pertence a algum Ejscritor
            if (escritorService.buscarPorCPF(cpf) != null || avaliadorService.buscarPorCPF(cpf) != null) {
                throw new CPFJaExistenteException("Já existe um usuário (Avaliador ou Escritor) cadastrado com este CPF.");
            }
            //------------------------------------------------------

            // Criação do objeto Avaliador
            String nome = nomeField.getText();
            //String cpf = cpfField.getText();
            String endereco = enderecoField.getText();
            String senha = senhaField.getText();

            Avaliador novoAvaliador = new Avaliador(nome, cpf, endereco, senha);

            // Salva no banco de dados através do serviço/DAO
            //AvaliadorService avaliadorService = new AvaliadorService();
            avaliadorService.salvar(novoAvaliador);

            System.out.println("Avaliador adicionado com sucesso: " + novoAvaliador.getNome());
            //Alerta de sucesso
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText(null);
            alert.setContentText("Avaliador registrado com sucesso!");
            alert.showAndWait();

            HelloApplication.telaDonoAvaliador();

            if (stage != null) {
                stage.close();
            }
        }catch(CPFJaExistenteException e){
            mostrarAlerta("CPF já cadastrado!", e.getMessage());
        } catch (CamposVaziosException e) {
            mostrarAlerta("Campos vazios!", e.getMessage());
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