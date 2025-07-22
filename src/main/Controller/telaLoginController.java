package main.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import main.Entities.Avaliador;
import main.Entities.Dono;
import main.Entities.Escritor;
import main.Model.Dao.ContaDAO;
import main.Entities.Conta;
import main.Model.Dao.EscritorDAO;
import main.Model.Service.AvaliadorService;
import main.View.HelloAplication;

public class telaLoginController implements Initializable {

    private Escritor escritor = new Escritor("Alisson", "135.822.114-60", "Equador-RN", "1012");
    EscritorDAO escritorDAO = new EscritorDAO();
    private Avaliador avaliador = new Avaliador("Guga", "111.111.111-11", "Mossoro rn", "10120201");
    AvaliadorService avaliadorService = new AvaliadorService();
    ContaDAO contaDAO = new ContaDAO();


    @FXML
    private Button botaoLogin;

    @FXML
    private PasswordField senha;

    @FXML
    private TextField cpfUsuario;

    @FXML
    void logar(ActionEvent event) {
        // Vai armazenar em contaAutenticada, a conta do BD que tem o CPF digitado na textfield
        Conta contaAutenticada = contaDAO.buscarPorCPF(cpfUsuario.getText());
        if (contaAutenticada != null && contaAutenticada.getSenha() != null && contaAutenticada.getSenha().equals(senha.getText())) {
            System.out.println("Login bem-sucedido para o usuário: " + contaAutenticada.getNome() + " (CPF: " + contaAutenticada.getCpf() + ")");
            if(contaAutenticada instanceof Dono){
                Dono dono = (Dono) contaAutenticada;
            }
            else if(contaAutenticada instanceof Avaliador){
                Avaliador avaliador =  (Avaliador) contaAutenticada;
                HelloAplication.obrasAvaliadas();
            }
            else if(contaAutenticada instanceof Escritor){
                Escritor escritor = (Escritor) contaAutenticada;
                HelloAplication.telaRegistrarObra();
            }

        } else {
            // CPF ou senha incorretos
            System.out.println("Usuário ou senha incorretos.");
            mostrarAlerta(Alert.AlertType.ERROR, "Dados incorretos", "Usuário ou senha não encontrados", "Usuário ou senha incorretos");
        }
    }
    private void mostrarAlerta(Alert.AlertType alertType, String titulo, String cabecalho, String mensagem) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        avaliadorService.salvar(avaliador);
        escritorDAO.salvar(escritor);
    }



}
