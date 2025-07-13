package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Entities.*;


import java.io.IOException;

public class HelloAplication extends Application {
    //Recebe os dados do login
    public static Conta contaLogada;

    public static Stage stage;
    public void start(Stage stage) throws Exception{
        this.stage = stage;
        telaRegistrarObra();
    }

    public static void telaLogin(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloAplication.class.getResource("tela-login.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 600);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void telaRegistrarObra(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloAplication.class.getResource("registrar-obra-interface.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 600);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void obrasAvaliadas() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloAplication.class.getResource("obras-avaliadas.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 600);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}