package main.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Entities.Avaliacoes;
import main.Entities.Avaliador;
import main.Entities.Obra;
// Supondo que você tenha DAOs para buscar os dados
import main.Model.Dao.AvaliadorDAO;
import main.Model.Dao.ObraDAO;
import main.Model.Service.AvaliacaoService;
import main.Model.Service.AvaliadorService;
import main.Model.Service.ObraService;
import main.view.HelloApplication;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DonoAvaliacaoController implements Initializable {

    private AvaliacaoService avaliacaoService;

    // --- Botões da Barra Lateral ---
    @FXML private Button btnObras;
    @FXML private Button btnAvaliador;
    @FXML private Button btnAutores;
    @FXML private Button btnAvaliacoes;
    @FXML private Button btnRelatorio;
    @FXML private Button btnLogout;


    // Tabela de Obras
    @FXML private TableView<Obra> tabelaObras;
    @FXML private TableColumn<Obra, String> tituloObraColumn;
    @FXML private TableColumn<Obra, String> autorObraColumn;

    // Tabela de Avaliadores
    @FXML private TableView<Avaliador> tabelaAvaliadores;
    @FXML private TableColumn<Avaliador, String> nomeAvaliadorColumn;

    // Botão de Ação
    @FXML private Button btnAtribuir;

    private ObservableList<Obra> obrasObservableList;
    private ObservableList<Avaliador> avaliadoresObservableList;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configura as colunas das duas tabelas
        configurarTabelas();
        // Carrega os dados do "banco"
        carregarDados();
        avaliacaoService = new AvaliacaoService();
    }

    private void configurarTabelas() {
        // Configuração da tabela de Obras
        tituloObraColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        autorObraColumn.setCellValueFactory(new PropertyValueFactory<>("autor"));

        // Configuração da tabela de Avaliadores
        nomeAvaliadorColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
    }

    private void carregarDados() {
        // Carrega Obras
        ObraService obraService = new ObraService();
        List<Obra> obrasDoBanco = obraService.BuscarTodos();
        obrasObservableList = FXCollections.observableArrayList(obrasDoBanco);
        tabelaObras.setItems(obrasObservableList);

        // Carrega Avaliadores
        AvaliadorService avaliadorService = new AvaliadorService();
        List<Avaliador> avaliadoresDoBanco = avaliadorService.BuscarTodos();
        avaliadoresObservableList = FXCollections.observableArrayList(avaliadoresDoBanco);
        tabelaAvaliadores.setItems(avaliadoresObservableList);
    }

    @FXML
    void handleAtribuir(ActionEvent event) {
        // Pega os itens selecionados de cada tabela
        Obra obraSelecionada = tabelaObras.getSelectionModel().getSelectedItem();
        Avaliador avaliadorSelecionado = tabelaAvaliadores.getSelectionModel().getSelectedItem();

        // Validação: verifica se ambos foram selecionados
        if (obraSelecionada == null || avaliadorSelecionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleção Incompleta", "Por favor, selecione uma obra e um avaliador para continuar.");
            return;
        }

        // Lógica de atribuição
        System.out.println("Atribuindo a obra '" + obraSelecionada.getTitulo() + "' ao avaliador '" + avaliadorSelecionado.getNome() + "'.");


        avaliacaoService.salvar(new Avaliacoes(avaliadorSelecionado ,obraSelecionada));

        mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Atribuição realizada com sucesso!");

        // Opcional: remover a obra da lista de disponíveis após a atribuição
        obrasObservableList.remove(obraSelecionada);
    }

    private void mostrarAlerta(Alert.AlertType alertType, String titulo, String mensagem) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Sistema da Editora");
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}