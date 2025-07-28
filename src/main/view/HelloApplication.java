package main.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Controller.AvaliacaoModalController;
import main.Controller.DataReceiver;
import main.Entities.Avaliacoes;
import main.Entities.Avaliador;
import main.Entities.Escritor;
import main.Entities.Obra;


import java.io.IOException;

public class HelloApplication extends Application {
    public static Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        telaRegistrarObra();
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
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlPath));

            Parent root = loader.load();

            Object controller = loader.getController();

            //  Verifica se o controller está pronto para receber dados
            if (data != null && controller instanceof DataReceiver) {
                // Se sim, faz o "cast" e chama o método initData para enviar o objeto
                ((DataReceiver<T>) controller).initData(data);
            }

            Scene scene = new Scene(root);
            mainStage.setTitle(windowTitle);
            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e) {
            System.err.println("Erro ao carregar FXML: " + fxmlPath);
            e.printStackTrace();
        }
    }

    public static void trocarTela(String fxmlPath, String windowTitle, Avaliacoes avaliacao , Avaliador avaliador) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlPath));

            Parent root = loader.load();

            AvaliacaoModalController controller = loader.getController();

            //  Verifica se o controller está pronto para receber dados
            if (avaliacao != null ) {
                // Se sim, faz o "cast" e chama o método initData para enviar o objeto
                 controller.initData(avaliacao,avaliador);
            }

            Scene scene = new Scene(root);
            mainStage.setTitle(windowTitle);
            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e) {
            System.err.println("Erro ao carregar FXML: " + fxmlPath);
            e.printStackTrace();
        }
    }



    public static void telaLogin() {
        trocarTela("/Telas/tela-login.fxml", "Tela de Login");
    }

    public static void telaDono() {
        trocarTela("/Telas/DashboardDono.fxml", "Tela do Dono");
    }

    public static void telaDonoEscritorAdicionar() {
        trocarTela("/Telas/NovoEscritorView.fxml", "Tela do Dono/Escritor add");
    }

    public static void telaDonoEscritorEditar(Escritor escritor) {
        trocarTela("/Telas/EditarEscritorView.fxml", "Tela do Dono/Avaliador add",escritor);
    }

    public static void telaDonoObra() {
        trocarTela("/Telas/DashboardDonoObra.fxml", "Tela do Dono/Obra");
    }

    public static void telaDonoObraAdicionar() {
        trocarTela("/Telas/NovaObraView.fxml", "Tela do Dono/Obra");
    }


    public static void telaDonoObraEditar(Obra obra) {
        trocarTela("/Telas/EditarObraView.fxml", "Tela do Dono/Avaliador add",obra);
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
        trocarTela("/Telas/DashboardDonoRelatorioAvaliacoes.fxml", "Tela do Dono/Relatorio");
    }

    public static void telaEscritorLogin(Escritor escritor) {
        trocarTela("/Telas/DashboardEscritorLogin.fxml", "Tela do Dono", escritor);
    }

    public static void telaAvaliadorLogin(Avaliador avaliador) {
        trocarTela("/Telas/DashboardAvaliador.fxml", "Tela do Dono", avaliador);
    }

    public static void telaAvaliadorAvaliado(Avaliador avaliador) {
        trocarTela("/Telas/DashboardAvaliadorAvaliado.fxml", "Tela do Dono",avaliador);
    }

    public static void telaAvaliadorAvaliar(Avaliacoes avaliacoe,Avaliador avaliador) {
        trocarTela("/Telas/DashboardAvaliadorAvaliar.fxml", "Tela do Dono",avaliacoe,avaliador);
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