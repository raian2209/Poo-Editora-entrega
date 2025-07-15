package main.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class HelloAplication extends Application {
    public static Stage stage;
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        telaRegistrarObra();
    }

    public void telaLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloAplication.class.getResource("/Telas/tela-login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void telaRegistrarObra(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloAplication.class.getResource("/Telas/registrar-obra-interface.fxml"));
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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloAplication.class.getResource("/Telas/obras-avaliadas.fxml"));
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