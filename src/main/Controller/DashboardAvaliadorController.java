package main.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.Entities.Avaliador;
import main.Entities.Avaliacoes;
import main.Entities.Obra;
import main.Entities.Status;
import main.Model.Service.AvaliacaoService;
import main.view.HelloApplication;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DashboardAvaliadorController implements DataReceiver<Avaliador> {


    private Avaliador avaliadorLogado;

    @FXML private Label nomeAvaliadorLabel;
    @FXML private Label tituloDaPagina;


    @FXML private TableView<Avaliacoes> tabelaAvaliacoes;


    @FXML private TableColumn<Avaliacoes, String> tituloColumn;
    @FXML private TableColumn<Avaliacoes, String> statusColumn;

    private ObservableList<Avaliacoes> avaliacoesObservableList;

    @Override
    public void initData(Avaliador avaliador) {
        this.avaliadorLogado = avaliador;
        nomeAvaliadorLabel.setText(avaliador.getNome());
        carregarObrasParaAvaliar(); // Carrega a tela inicial
    }

    @FXML
    public void initialize() {
        // --- Configuração das Colunas ---
        // expressão lambda para "ensinar" a coluna a buscar o dado


        tituloColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getObraAvaliar().getTitulo())
        );


        statusColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getStatus())
        );

        tabelaAvaliacoes.setPlaceholder(new Label("Nenhuma obra encontrada nesta categoria."));
    }


    void carregarObrasParaAvaliar() {
        if (avaliadorLogado == null) return;
        tituloDaPagina.setText("Obras Pendentes de Avaliação");

        AvaliacaoService avaliacaoService = new AvaliacaoService();
        List<Avaliacoes> avaliacoesPendentes = avaliacaoService.avaliacoesNaoAvaliadas(avaliadorLogado);

        avaliacoesObservableList = FXCollections.observableArrayList(avaliacoesPendentes);
        tabelaAvaliacoes.setItems(avaliacoesObservableList);
    }



    @FXML
    void handleAvaliarObra(ActionEvent event) {

        Avaliacoes avaliacaoSelecionada = tabelaAvaliacoes.getSelectionModel().getSelectedItem();

        if (avaliacaoSelecionada != null) {
            System.out.println("Avaliando a obra: " + avaliacaoSelecionada.getObraAvaliar().getTitulo());
            HelloApplication.telaAvaliadorAvaliar(avaliacaoSelecionada,avaliadorLogado);
        } else {

        }
    }

    @FXML
    void handleLogout(ActionEvent event) {
        HelloApplication.telaLogin();
    }
    @FXML
    void handleObrasAvaliadas(ActionEvent event){
        HelloApplication.telaAvaliadorAvaliado(avaliadorLogado);
    }
    @FXML
    void handleMostrarObrasParaAvaliar(ActionEvent event){
        HelloApplication.telaAvaliadorLogin(avaliadorLogado);
    }
}