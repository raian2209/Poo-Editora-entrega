package main.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Entities.Escritor;
import main.Exceptions.*;
import main.Model.Dao.ContaDAO;
import main.Model.Service.EscritorService;
import main.view.HelloApplication;
import main.Model.Service.AvaliadorService;
import main.Exceptions.SenhaJaExistenteException;

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
        try {// Validação dos campos
            if (nomeField.getText().isEmpty() || cpfField.getText().isEmpty() || enderecoField.getText().isEmpty() || senhaField.getText().isEmpty()) {
                throw new CamposVaziosException("Por favor, preencha todos os campos para adicionar o escritor.");
            }

            // Verificação se já existe avaliador ou escritor com o cpf passado

            String cpf = cpfField.getText();

            if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
                throw new CPFInvalidoException("O formato do CPF está inválido. Use o formato XXX.XXX.XXX-XX.");
            }

            EscritorService escritorService = new EscritorService();
            AvaliadorService avaliadorService = new AvaliadorService();

            if (escritorService.buscarPorCPF(cpf) != null || avaliadorService.buscarPorCPF(cpf) != null) {
                throw new CPFJaExistenteException("Já existe um usuário (Avaliador ou Escritor) cadastrado com este CPF.");
            }
            // Verificar se a senha passada já existe
            String senha1 = senhaField.getText();
            ContaDAO contaDAO = new ContaDAO();

            if ((contaDAO.buscarPorSenha(senhaField.getText()) != null) || senhaField.getText().equals("123")) {
                throw new SenhaJaExistenteException("Esta senha já está em uso. Por favor, escolha outra.");
            }
            //-------------

            //movi cpf e Escritor service mais pra cima, pra verificar se o CPF já foi cadastrado
            // Criação do objeto Escritor
            String nome = nomeField.getText();
            //String cpf = cpfField.getText();
            String endereco = enderecoField.getText();
            String senha = senhaField.getText();

            Escritor novoEscritor = new Escritor(nome, cpf, endereco, senha);

            // Salva no banco de dados através do serviço/DAO
            //EscritorService escritorService = new EscritorService();
            escritorService.salvar(novoEscritor);

            System.out.println("Escritor adicionado com sucesso: " + novoEscritor.getNome());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText(null);
            alert.setContentText("Escritor criado com sucesso!");
            alert.showAndWait();
            HelloApplication.telaDono();

            if (stage != null) {
                stage.close();
            }
        }catch(CPFJaExistenteException e){
            mostrarAlerta("CPF já cadastrado!", e.getMessage());
        } catch (CPFInvalidoException e) {
            mostrarAlerta("CPF Inválido!", e.getMessage());
        } catch (CamposVaziosException e) {
            mostrarAlerta("Campos vazios!", e.getMessage());
        }catch(SenhaJaExistenteException e){
            mostrarAlerta("Senha já existente!", e.getMessage());
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