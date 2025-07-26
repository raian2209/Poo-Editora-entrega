package main.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Entities.Avaliador;
import main.Entities.Obra;
import main.Model.Service.ObraService;
import main.view.HelloApplication;

import java.util.List;

public class DonoGerenciarObrasController {

    @FXML
    private TableView<Obra> tabelaObras; // Tabela que exibirá objetos do tipo Obra


    @FXML
    private TableColumn<Obra, String> nomeDaObraColumn; // Coluna da tabela Obra que exibirá uma String

    @FXML
    private TableColumn<Obra, String> escritorColumn; // Coluna da tabela Obra que exibirá uma String

    private ObservableList<Obra> obrasObservableList;

    @FXML private Button btnObras;
    @FXML private Button btnAvaliador;
    @FXML private Button btnAutores;
    @FXML private Button btnAvaliacoes;
    @FXML private Button btnRelatorio;
    @FXML private Button btnLogout;

    ObraService obraService;

    @FXML
    private TableView<Obra> tabelaAvaliadores; // Troque '?' pela sua classe de entidade Avaliador

    @FXML
    void handleNavegarObras(ActionEvent event) {
        HelloApplication.telaDonoObra();
    }

    @FXML
    void handleNavegarAvaliador(ActionEvent event) {
        HelloApplication.telaDonoAvaliador();
    }

    @FXML
    void handleNavegarAutores(ActionEvent event) {
        HelloApplication.telaDono();
    }

    @FXML
    void handleNavegarAvaliacoes(ActionEvent event) {
        HelloApplication.telaDonoAvaliacoes();
    }

    @FXML
    void handleNavegarRelatorio(ActionEvent event) {
        HelloApplication.telaDonoRelatorio();
    }

    @FXML
    void handleLogout(ActionEvent event) {
        HelloApplication.telaLogin();
    }

    @FXML
    void adicionarObra(ActionEvent event) {
        // TODO: Lógica para pegar o item selecionado na tabela e abrir tela de edição.
    }

    @FXML
    void atualizarObra(ActionEvent event) {
        // TODO: Lógica para pegar o item selecionado na tabela e abrir tela de edição.
    }

    @FXML
    void deletarObra(ActionEvent event) {
        Obra obraSelecionado = tabelaObras.getSelectionModel().getSelectedItem();
        obraService.deletar(obraSelecionado);
        obrasObservableList.remove(obraSelecionado);
    }

    @FXML
    public void initialize() {
        configurarColunas();
        carregarDadosNaTabela();
        obraService = new ObraService();

    }

    private void carregarDadosNaTabela() {
        ObraService obraService = new ObraService();

        List<Obra> obrasDoBanco = obraService.BuscarTodos();
        obrasObservableList = FXCollections.observableArrayList(obrasDoBanco);
        tabelaObras.setItems(obrasObservableList);

    }

    private void configurarColunas() {
        // Para cada coluna, definimos uma "fábrica de valor de célula".
        // O PropertyValueFactory é a forma mais simples. O nome em aspas ("titulo")
        // DEVE ser exatamente o nome da propriedade na classe Obra (derivado do método tituloProperty()).

        // No seu FXML, você precisa adicionar fx:id="nomeDaObraColumn" e fx:id="escritorColumn"
        // às suas TableColumns correspondentes.
        nomeDaObraColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        escritorColumn.setCellValueFactory(new PropertyValueFactory<>("autor"));
    }


}
