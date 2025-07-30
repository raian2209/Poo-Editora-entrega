package main.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    @FXML private TextField pesquisar;
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
        HelloApplication.telaDonoObraAdicionar();
    }

    @FXML
    void atualizarObra(ActionEvent event) {
        // TODO: Lógica para pegar o item selecionado na tabela e abrir tela de edição.
        Obra obraSelecionado = tabelaObras.getSelectionModel().getSelectedItem();

        // 2. Verifica se um item foi realmente selecionado
        if (obraSelecionado != null) {

            HelloApplication.telaDonoObraEditar(obraSelecionado);

        }else{
            mostrarAlerta("Nenhuma Seleção", "Por favor, selecione uma obra na tabela para editar.");
        }
    }

    @FXML
    void deletarObra(ActionEvent event) {
        Obra obraSelecionado = tabelaObras.getSelectionModel().getSelectedItem();

        //verificação e o alerta de confirmação.
        if (obraSelecionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Tem certeza que deseja deletar a obra '" + obraSelecionado.getTitulo() + "'?", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Confirmação de Exclusão");
            alert.setHeaderText(null);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    obraService.deletar(obraSelecionado);
                    obrasObservableList.remove(obraSelecionado);
                }
            });
        } else {
            // Se nenhuma obra for selecionada, também mostra um alerta.
            mostrarAlerta("Nenhuma Seleção", "Por favor, selecione uma obra na tabela para deletar.");
        }
    }


    @FXML
    public void initialize() {
        configurarColunas();
        carregarDadosNaTabela();
        obraService = new ObraService();

    }

    @FXML
    void handlePesquisar(ActionEvent event) {
        String tituloPesquisado = pesquisar.getText();
        if (tituloPesquisado == null || tituloPesquisado.isBlank()) {
            carregarDadosNaTabela(); // Se a busca for vazia, mostra todos
            return;
        }

        // Busca no serviço apenas por título
        List<Obra> buscarResultados = obraService.buscarPorTitulo(tituloPesquisado);

        obrasObservableList = FXCollections.observableArrayList(buscarResultados);
        tabelaObras.setItems(obrasObservableList);
        tabelaObras.setPlaceholder(new Label("Nenhuma obra encontrada para a sua pesquisa."));
    }

    @FXML
    void handleLimparPesquisa(ActionEvent event) {
        pesquisar.clear();
        carregarDadosNaTabela();
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

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Atenção");
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
//--------------------------------CHECKPOINT
