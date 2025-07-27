package main.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Controller.DataReceiver;
import main.Entities.Avaliador;


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

    public static <T> void trocarTela(String fxmlPath, String windowTitle, T data) {
        try {
            // 1. Cria a instância do FXMLLoader
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlPath));

            // 2. Carrega o FXML. Isso cria a instância do controller associado.
            Parent root = loader.load();

            // 3. Pega a referência do controller que acabou de ser criado
            Object controller = loader.getController();

            // 4. Verifica se o controller está pronto para receber dados
            if (data != null && controller instanceof DataReceiver) {
                // Se sim, faz o "cast" e chama o método initData para enviar o objeto
                ((DataReceiver<T>) controller).initData(data);
            }

            // 5. Cria a nova cena e a define no palco principal
            Scene scene = new Scene(root);
            mainStage.setTitle(windowTitle);
            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e) {
            System.err.println("Erro ao carregar FXML: " + fxmlPath);
            e.printStackTrace();
        }
    }


    // Agora os métodos específicos apenas chamam o método genérico
    public static void telaLogin() {
        trocarTela("/Telas/tela-login.fxml", "Tela de Login");
    }

    public static void telaDono() {
        trocarTela("/Telas/DashboardDono.fxml", "Tela do Dono");
    }

    public static void telaDonoEscritorAdicionar() {
        trocarTela("/Telas/NovoEscritorView.fxml", "Tela do Dono/Escritor add");
    }
    public static void telaDonoObra() {
        trocarTela("/Telas/DashboardDonoObra.fxml", "Tela do Dono/Obra");
    }

    public static void telaDonoObraAdicionar() {
        trocarTela("/Telas/NovaObraView.fxml", "Tela do Dono/Obra");
    }


    public static void telaDonoAvaliador() {
        trocarTela("/Telas/DashboardDonoAvaliador.fxml", "Tela do Dono/Avaliador");
    }
    public static void telaDonoAvaliadorAdicionar() {
        trocarTela("/Telas/NovoAvaliadorView.fxml", "Tela do Dono/Avaliador add");
    }
    public static void telaDonoAvaliadorEditar(Avaliador avaliador) {
        trocarTela("/Telas/EditarAvaliadorView.fxml", "Tela do Dono/Avaliador add",avaliador);
    }

    public static void telaDonoAvaliacoes() {
        trocarTela("/Telas/DashboardDonoAvaliacao.fxml", "Tela do Dono/Avaliacoes");
    }
    public static void telaDonoRelatorio() {
        trocarTela("/Telas/DashboardDonoAvaliador.fxml", "Tela do Dono/Avaliador");
    }

    public static void telaAvaliadorInicial() {
        trocarTela("/Telas/DashboardAvaliador.fxml", "Tela do Dono");
    }
    public static void telaEscritorInicial() {
        trocarTela("/Telas/DashboardEscritor.fxml", "Tela do Dono");
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