package main.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.Entities.Avaliacoes;
import main.Model.Service.AvaliacaoService;
import main.view.HelloApplication;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class RelatorioAvaliacoesController implements Initializable {

    @FXML private DatePicker datePickerFiltro;
    @FXML private Spinner<Integer> hourSpinner;
    @FXML private Spinner<Integer> minuteSpinner;



    @FXML private TableView<Avaliacoes> tabelaAvaliacoes;
    @FXML private TableColumn<Avaliacoes, String> tituloObraColumn;
    @FXML private TableColumn<Avaliacoes, String> nomeAvaliadorColumn;
    @FXML private TableColumn<Avaliacoes, String> statusColumn;
    @FXML private TableColumn<Avaliacoes, String> dataColumn;

    private ObservableList<Avaliacoes> avaliacoesObservableList;
    private AvaliacaoService avaliacaoService = new AvaliacaoService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColunas();
        configurarSpinners();
        carregarTodasAvaliacoes();
    }


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



    private void configurarSpinners() {
        SpinnerValueFactory<Integer> hourFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0); // min, max, initial
        hourSpinner.setValueFactory(hourFactory);
        hourSpinner.setEditable(true);
        SpinnerValueFactory<Integer> minuteFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0); // min, max, initial
        minuteSpinner.setValueFactory(minuteFactory);
        minuteSpinner.setEditable(true);
    }

    private void configurarColunas() {
        // Usa lambdas para buscar dados de objetos aninhados
        tituloObraColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getObraAvaliar().getTitulo())
        );
        nomeAvaliadorColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAvaliadorObra().getNome())
        );
        statusColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getStatus())
        );
        // Formata a data para exibição
        dataColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue().getDataAvaliacao() != null) {
                String dataFormatada = cellData.getValue().getDataAvaliacao()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                return new SimpleStringProperty(dataFormatada);
            }
            return new SimpleStringProperty("N/A");
        });
    }

    private void carregarTodasAvaliacoes() {
        List<Avaliacoes> todasAvaliacoes = avaliacaoService.avaliacoesAvaliadas();
        avaliacoesObservableList = FXCollections.observableArrayList(todasAvaliacoes);
        tabelaAvaliacoes.setItems(avaliacoesObservableList);
        tabelaAvaliacoes.setPlaceholder(new Label("Nenhuma avaliação encontrada."));
    }

    @FXML
    void handleFiltrar(ActionEvent event) {
        LocalDate selectedDate = datePickerFiltro.getValue();
        if (selectedDate == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Data não selecionada", "Por favor, selecione uma data para filtrar.", "Por favor, selecione uma data para filtrar.");
            return;
        }


        Integer hour = hourSpinner.getValue();
        Integer minute = minuteSpinner.getValue();

        LocalDateTime selectedDateTime = selectedDate.atTime(hour, minute);

        System.out.println("Filtrando avaliações a partir de: " + selectedDateTime);


        List<Avaliacoes> avaliacoesFiltradas = avaliacaoService.avaliadoAteData(selectedDateTime);

        avaliacoesObservableList = FXCollections.observableArrayList(avaliacoesFiltradas);
        tabelaAvaliacoes.setItems(avaliacoesObservableList);
    }

    @FXML
    void handleLimparFiltro(ActionEvent event) {
        datePickerFiltro.setValue(null);
        hourSpinner.getValueFactory().setValue(0);
        minuteSpinner.getValueFactory().setValue(0);
        carregarTodasAvaliacoes();
    }
    private void mostrarAlerta(Alert.AlertType alertType, String titulo, String cabecalho, String mensagem) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

}