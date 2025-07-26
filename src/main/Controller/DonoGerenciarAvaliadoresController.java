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
import main.Entities.Obra;
import main.Model.Service.AvaliadorService;
import main.view.HelloApplication;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DonoGerenciarAvaliadoresController implements Initializable {

    @FXML
    private TableColumn<Avaliador, String> nomeColumn;

    @FXML
    private TableColumn<Avaliador, String> cpfColumn;

    @FXML
    private TableColumn<Avaliador, String> enderecoColumn;

    private ObservableList<Avaliador> avaliadoresObservableList;

    @FXML private Button btnObras;
    @FXML private Button btnAvaliador;
    @FXML private Button btnAutores;
    @FXML private Button btnAvaliacoes;
    @FXML private Button btnRelatorio;
    @FXML private Button btnLogout;

    AvaliadorService avaliadorService;

    @FXML
    private TableView<Avaliador> tabelaAvaliadores; // Troque '?' pela sua classe de entidade Avaliador

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
    void adicionarAvaliador(ActionEvent event) {
        // TODO: Lógica para abrir tela de cadastro de avaliador.
    }

    @FXML
    void atualizarAvaliador(ActionEvent event) {
        // TODO: Lógica para pegar o item selecionado na tabela e abrir tela de edição.
    }

    @FXML
    void deletarAvaliador(ActionEvent event) {
        Avaliador avaliadorSelecionado = tabelaAvaliadores.getSelectionModel().getSelectedItem();
        avaliadorService.deletar(avaliadorSelecionado);
        avaliadoresObservableList.remove(avaliadorSelecionado);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Método executado quando a tela é carregada
        configurarColunas();
        carregarDadosNaTabela();
        avaliadorService= new AvaliadorService();
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
        AvaliadorService avaliadorService = new AvaliadorService();

        // Busca a lista de avaliadores
        List<Avaliador> avaliadoresDoBanco = avaliadorService.BuscarTodos();

        // Converte para uma ObservableList
        avaliadoresObservableList = FXCollections.observableArrayList(avaliadoresDoBanco);

        // Popula a tabela
        tabelaAvaliadores.setItems(avaliadoresObservableList);
    }


}