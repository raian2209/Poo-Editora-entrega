package main.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Entities.Escritor;
import main.Entities.Obra;
import main.Model.Service.ObraService;
import main.view.HelloApplication;

import java.util.List;

public class DashboardEscritorController implements DataReceiver<Escritor> {

    private Escritor escritorLogado; // Armazena os dados do escritor que fez login

    @FXML private Label nomeEscritorLabel;
    @FXML private TableView<Obra> tabelaObras;
    @FXML private TableColumn<Obra, String> tituloColumn;
    @FXML private TableColumn<Obra, Integer> anoColumn;

    private ObservableList<Obra> obrasObservableList;

    /**
     * Este método é chamado pela classe de navegação para passar o escritor que fez login.
     */
    @Override
    public void initData(Escritor escritor) {
        this.escritorLogado = escritor;
        nomeEscritorLabel.setText(escritor.getNome());


        carregarObrasDoEscritor();
    }

    @FXML
    public void initialize() {

        tituloColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        anoColumn.setCellValueFactory(new PropertyValueFactory<>("ano"));
    }

    private void carregarObrasDoEscritor() {
        if (escritorLogado == null) return;
        System.out.println(escritorLogado);
        ObraService obraService = new ObraService();
        List<Obra> obrasDoBanco = obraService.buscarPorEscritor(escritorLogado);

        obrasObservableList = FXCollections.observableArrayList(obrasDoBanco);
        tabelaObras.setItems(obrasObservableList);
    }



    @FXML
    void handleLogout(ActionEvent event) {
        // Volta para a tela de login
        HelloApplication.telaLogin();
    }
}