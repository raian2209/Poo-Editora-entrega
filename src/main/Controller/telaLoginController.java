package main.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


import main.Entities.Avaliador;
import main.Entities.Dono;
import main.Entities.Escritor;
import main.Exceptions.UsuarioOuSenhaIncorretosException;
import main.Model.Dao.ContaDAO;
import main.Entities.Conta;
import main.Model.Dao.EscritorDAO;
import main.Model.Factory.ContaFactory;
import main.Model.Service.AvaliadorService;
import main.Model.Strategy.AvaliadorLoginStrategy;
import main.Model.Strategy.DonoLoginStrategy;
import main.Model.Strategy.EscritorLoginStrategy;
import main.Model.Strategy.LoginSuccessStrategy;
import main.view.HelloApplication;

public class telaLoginController implements Initializable {

    // O mapa de estratégias
    private final Map<Class<? extends Conta>, LoginSuccessStrategy> strategyMap = new HashMap<>();

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

    public telaLoginController() {
        // Pré-carrega o mapa de estratégias no construtor
        strategyMap.put(Avaliador.class, new AvaliadorLoginStrategy());
        strategyMap.put(Escritor.class, new EscritorLoginStrategy());
        strategyMap.put(Dono.class, new DonoLoginStrategy());
    }

    @FXML
    void logar(ActionEvent event) {
        // Vai armazenar em contaAutenticada, a conta do BD que tem o CPF digitado na textfield
        try{
        Conta contaAutenticada = ContaFactory.createConta(contaDAO.buscarPorCPF(cpfUsuario.getText()));


        if (contaAutenticada != null && contaAutenticada.getSenha() != null && contaAutenticada.getSenha().equals(senha.getText())) {
            System.out.println("Login bem-sucedido para o usuário: " + contaAutenticada.getNome() + " (CPF: " + contaAutenticada.getCpf() + ")");

            // Pega a estratégia correta do mapa usando a classe do objeto autenticado
            LoginSuccessStrategy strategy = strategyMap.get(contaAutenticada.getClass());

            if (strategy != null) {
                // Executa a estratégia (navegação, etc.)
                strategy.execute();
            } else {
                System.out.println("Nenhuma ação definida para o tipo de conta: " + contaAutenticada.getClass().getSimpleName());
            }

        } else {

            throw new UsuarioOuSenhaIncorretosException("Usuario ou Senha não reconhecido: " + cpfUsuario.getText() + " "+ senha.getText());

        }
        } catch (IllegalArgumentException e){
            mostrarAlerta(Alert.AlertType.ERROR, "Dados Não reconhecidos", "Tipo de usuario não encontrado", "Tipo de Usuario não reconhecido");
        } catch (UsuarioOuSenhaIncorretosException e){
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
