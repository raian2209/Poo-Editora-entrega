package main.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class telaLoginController implements Initializable {

    @FXML
    private Button botaoLogin;

    @FXML
    private PasswordField senha;

    @FXML
    private TextField usuario;

    @FXML
    void logar(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb){

    }

}
