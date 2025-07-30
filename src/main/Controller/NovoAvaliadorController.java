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
import main.Exceptions.SenhaJaExistenteException;
import main.Model.Dao.ContaDAO;
import main.Model.Service.AvaliadorService;
import main.Model.Service.EscritorService;
import main.view.HelloApplication;

public class NovoAvaliadorController {

    //OBS: FALTA FAZER ALGUMAS VERIFICAÇÕES

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
                throw new CamposVaziosException("Por favor, preencha todos os campos para adicionar o avaliador.");
            }

            // Verificação de CPF(E verificado tbm se já existem Escritores com o CPF passado)
            String cpf = cpfField.getText();

            if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
                throw new CPFInvalidoException("O formato do CPF está inválido. Use o formato XXX.XXX.XXX-XX.");
            }
            EscritorService escritorService = new EscritorService();
            AvaliadorService avaliadorService = new AvaliadorService();

            // verificação se o CPF JÁ pertence a algum Ejscritor
            if (escritorService.buscarPorCPF(cpf) != null || avaliadorService.buscarPorCPF(cpf) != null) {
                throw new CPFJaExistenteException("Já existe um usuário (Avaliador ou Escritor) cadastrado com este CPF.");
            }

            ContaDAO contaDAO = new ContaDAO();
            if (contaDAO.buscarPorSenha(senhaField.getText()) != null || senhaField.getText().equals("123")) {
                throw new SenhaJaExistenteException("Esta senha já está em uso ou é inválida. Por favor, escolha outra.");
            }

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
        }catch (CPFInvalidoException e) {
            mostrarAlerta("CPF Inválido!", e.getMessage());
        } catch (CamposVaziosException e) {
            mostrarAlerta("Campos vazios!", e.getMessage());
        }catch(SenhaJaExistenteException e){
            mostrarAlerta("Senha já existente!", e.getMessage());
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