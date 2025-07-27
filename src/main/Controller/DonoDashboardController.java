package main.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Entities.Avaliador;
import main.Entities.Escritor;
import main.Model.Service.AvaliadorService;
import main.Model.Service.EscritorService;
import main.view.HelloApplication;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DonoDashboardController implements Initializable {


    @FXML private TableView<Escritor> tabelaEscritor; // Tabela que exibirá objetos do tipo Obra

    @FXML private TableColumn<Escritor, String> nomeColumn; // Coluna da tabela Obra que exibirá uma String

    @FXML private TableColumn<Escritor, String> cpfColumn;// Coluna da tabela Obra que exibirá uma String

    @FXML private TableColumn<Escritor, String> enderecoColumn;

    private ObservableList<Escritor> escritorObservableList;

    // --- Botões da Barra Lateral ---
    @FXML private Button btnObras;
    @FXML private Button btnAvaliador;
    @FXML private Button btnAutores;
    @FXML private Button btnAvaliacoes;
    @FXML private Button btnRelatorio;
    @FXML private Button btnLogout;

    EscritorService escritorService;
    // --- Componentes da Tela de Autores (Exemplo) ---


    // --- Métodos de Ação (Handlers) ---
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
    void adicionarAutor(ActionEvent event) {
        HelloApplication.telaDonoEscritorAdicionar();
    }
    @FXML
    void atualizarAutor(ActionEvent event) {

    }
    @FXML
    void deletarAutor(ActionEvent event) {
        Escritor escritorSelecionado = tabelaEscritor.getSelectionModel().getSelectedItem();
        escritorService.deletar(escritorSelecionado);
        escritorObservableList.remove(escritorSelecionado);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Método executado quando a tela é carregada
        configurarColunas();
        carregarDadosNaTabela();
        escritorService = new EscritorService();
    }

    private void configurarColunas() {
        // 3. Conectar cada coluna com a propriedade correspondente da classe Avaliador
        // O nome em aspas ("nome", "cpf") deve ser IDÊNTICO ao nome da propriedade.
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        enderecoColumn.setCellValueFactory(new PropertyValueFactory<>("endereco"));
    }

    private void carregarDadosNaTabela() {
        // Instancia o DAO
        EscritorService escritorService = new EscritorService();

        // Busca a lista de avaliadores
        List<Escritor> escritoresDoBanco = escritorService.BuscarTodos();

        // Converte para uma ObservableList
        escritorObservableList = FXCollections.observableArrayList(escritoresDoBanco);

        // Popula a tabela
        tabelaEscritor.setItems(escritorObservableList);
    }
}