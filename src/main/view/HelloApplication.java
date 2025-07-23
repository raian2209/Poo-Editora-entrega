package main.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class HelloApplication extends Application {
    public static Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        telaLogin();
    }


    /**
     * Método centralizado para trocar de cena no palco principal.
     * @param fxmlPath O caminho para o arquivo FXML a ser carregado.
     * @param windowTitle O novo título para a janela.
     */
    public static void trocarTela(String fxmlPath, String windowTitle) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlPath));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            mainStage.setTitle(windowTitle);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            System.err.println("Erro ao carregar o FXML: " + fxmlPath);
            throw new RuntimeException(e);
        }
    }

    // Agora os métodos específicos apenas chamam o método genérico
    public static void telaLogin() {
        trocarTela("/Telas/tela-login.fxml", "Tela de Login");
    }

    public static void telaRegistrarObra() {
        trocarTela("/Telas/registrar-obra-interface.fxml", "Registrar Obra");
    }

    public static void obrasAvaliadas() {
        trocarTela("/Telas/obras-avaliadas.fxml", "Obras Avaliadas");
    }

    

    public static void main(String[] args) {
        launch(args);
    }
}