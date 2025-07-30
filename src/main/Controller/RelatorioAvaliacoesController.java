package main.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import main.Entities.Avaliacoes;
import main.Model.Service.AvaliacaoService;
import main.view.HelloApplication;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RelatorioAvaliacoesController implements Initializable {

    // Componentes do FXML
    @FXML private DatePicker datePickerFiltro;
    @FXML private Spinner<Integer> hourSpinner;
    @FXML private Spinner<Integer> minuteSpinner;
    @FXML private TableView<Avaliacoes> tabelaAvaliacoes;
    @FXML private TableColumn<Avaliacoes, String> tituloObraColumn;
    @FXML private TableColumn<Avaliacoes, String> nomeAvaliadorColumn;
    @FXML private TableColumn<Avaliacoes, String> statusColumn;
    @FXML private TableColumn<Avaliacoes, String> dataColumn;
    @FXML private TableColumn<Avaliacoes, String> justificativaColumn;
    @FXML private Button btnBaixarPDF;

    private ObservableList<Avaliacoes> avaliacoesObservableList;
    private final AvaliacaoService avaliacaoService = new AvaliacaoService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColunas();
        configurarSpinners();
        carregarTodasAvaliacoes();
    }

    @FXML
    void handleBaixarPDF(ActionEvent event) {
        List<Avaliacoes> listaParaExportar = tabelaAvaliacoes.getItems();
        if (listaParaExportar.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Tabela Vazia", null, "Não há dados na tabela para exportar.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Relatório PDF");
        fileChooser.setInitialFileName("");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivo PDF", "*.pdf"));
        File file = fileChooser.showSaveDialog(btnBaixarPDF.getScene().getWindow());

        if (file != null) {
            try {
                gerarPdfComoLista(file, listaParaExportar);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", null, "O relatório PDF foi salvo com sucesso!");
            } catch (IOException e) {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro ao Gerar PDF", null, "Ocorreu um erro ao tentar salvar o arquivo.");
                e.printStackTrace();
            }
        }
    }

    private void gerarPdfComoLista(File file, List<Avaliacoes> avaliacoes) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            PDType1Font fontBold = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
            PDType1Font fontRegular = new PDType1Font(Standard14Fonts.FontName.HELVETICA);

            contentStream.beginText();
            contentStream.setFont(fontBold, 18);
            contentStream.newLineAtOffset(50, 780);
            contentStream.showText("Relatório de Avaliações");
            contentStream.endText();

            float yPosition = 740;
            float margin = 50;
            float leading = 15f;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            for (Avaliacoes av : avaliacoes) {
                // Se não tiver espaço na página, cria uma nova
                if (yPosition < 150) {
                    contentStream.close();
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = 780;
                }

                yPosition = escreverCampoComQuebra(contentStream, fontBold, fontRegular, margin, yPosition, leading, "Título da Obra: ", av.getObraAvaliar().getTitulo());
                yPosition = escreverCampoComQuebra(contentStream, fontBold, fontRegular, margin, yPosition, leading, "Nome do Avaliador: ", av.getAvaliadorObra().getNome());
                yPosition = escreverCampoComQuebra(contentStream, fontBold, fontRegular, margin, yPosition, leading, "Status da Avaliação: ", av.getStatus());
                String dataFormatada = (av.getDataAvaliacao() != null) ? av.getDataAvaliacao().format(formatter) : "Pendente";
                yPosition = escreverCampoComQuebra(contentStream, fontBold, fontRegular, margin, yPosition, leading, "Data da Avaliação: ", dataFormatada);
                String justificativa = av.getJustificativa() != null ? av.getJustificativa() : "Nenhuma justificativa fornecida.";
                yPosition = escreverCampoComQuebra(contentStream, fontBold, fontRegular, margin, yPosition, leading, "Justificativa: ", justificativa);

                // Adiciona um separador
                yPosition -= leading / 2;
                contentStream.moveTo(margin, yPosition);
                contentStream.lineTo(PDRectangle.A4.getWidth() - margin, yPosition);
                contentStream.stroke();
                yPosition -= leading * 1.5;
            }

            contentStream.close();
            document.save(file);
        }
    }

    private float escreverCampoComQuebra(PDPageContentStream stream, PDType1Font fontBold, PDType1Font fontRegular, float margin, float y, float leading, String titulo, String texto) throws IOException {
        float fontSize = 12;
        float pageContentWidth = PDRectangle.A4.getWidth() - (2 * margin);

        // Escreve o título em negrito
        stream.beginText();
        stream.setFont(fontBold, fontSize);
        stream.newLineAtOffset(margin, y);
        stream.showText(titulo);
        stream.endText();

        float titleWidth = fontBold.getStringWidth(titulo) / 1000 * fontSize;

        // Lógica de quebra de linha
        List<String> lines = new ArrayList<>();
        String[] words = texto.split(" ");
        StringBuilder currentLine = new StringBuilder();

        float availableWidth = pageContentWidth - titleWidth;

        for (String word : words) {
            if (word.isEmpty()) continue;

            // Lógica para quebrar palavras que sozinhas são maiores que a linha
            while (fontRegular.getStringWidth(word) / 1000 * fontSize > availableWidth) {
                int breakPoint = findBreakPoint(word, fontRegular, fontSize, availableWidth);
                lines.add(currentLine.toString() + word.substring(0, breakPoint));
                currentLine.setLength(0);
                word = word.substring(breakPoint);
                availableWidth = pageContentWidth;
            }

            float width = fontRegular.getStringWidth(currentLine + " " + word) / 1000 * fontSize;

            if (width < availableWidth) {
                if (!currentLine.isEmpty()) currentLine.append(" ");
                currentLine.append(word);
            } else {
                lines.add(currentLine.toString());
                currentLine = new StringBuilder(word);
                availableWidth = pageContentWidth;
            }
        }
        lines.add(currentLine.toString());

        // Escreve as linhas
        stream.setFont(fontRegular, fontSize);
        boolean firstLine = true;
        for (String line : lines) {
            stream.beginText();
            if (firstLine) {
                stream.newLineAtOffset(margin + titleWidth, y);
                firstLine = false;
            } else {
                y -= leading;
                stream.newLineAtOffset(margin, y);
            }
            stream.showText(line);
            stream.endText();
        }

        return y - leading;
    }

    private int findBreakPoint(String text, PDType1Font font, float fontSize, float availableWidth) throws IOException {
        int start = 0;
        int end = text.length();
        int breakPoint = 0;
        while(start <= end) {
            int mid = start + (end-start)/2;
            if (mid == 0) return 1; // Garante progresso mínimo
            float width = font.getStringWidth(text.substring(0, mid)) / 1000 * fontSize;
            if (width <= availableWidth) {
                breakPoint = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return breakPoint;
    }


    private void configurarColunas() {
        tituloObraColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getObraAvaliar().getTitulo()));
        nomeAvaliadorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAvaliadorObra().getNome()));
        statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
        dataColumn.setCellValueFactory(cellData -> {
            LocalDateTime data = cellData.getValue().getDataAvaliacao();
            return new SimpleStringProperty(data != null ? data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) : "Pendente");
        });
        justificativaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getJustificativa()));
    }

    //Restanto do código
    private void carregarTodasAvaliacoes() {
        List<Avaliacoes> todasAvaliacoes = avaliacaoService.avaliacoesAvaliadas();
        avaliacoesObservableList = FXCollections.observableArrayList(todasAvaliacoes);
        tabelaAvaliacoes.setItems(avaliacoesObservableList);
        tabelaAvaliacoes.setPlaceholder(new Label("Nenhuma avaliação encontrada."));
    }
    @FXML void handleFiltrar(ActionEvent event) {
        LocalDate selectedDate = datePickerFiltro.getValue();
        if (selectedDate == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Data não selecionada", "Por favor, selecione uma data para filtrar.", "Por favor, selecione uma data para filtrar.");
            return;
        }
        LocalDateTime selectedDateTime = selectedDate.atTime(hourSpinner.getValue(), minuteSpinner.getValue());
        List<Avaliacoes> avaliacoesFiltradas = avaliacaoService.avaliadoAteData(selectedDateTime);
        tabelaAvaliacoes.setItems(FXCollections.observableArrayList(avaliacoesFiltradas));
    }
    @FXML void handleLimparFiltro(ActionEvent event) {
        datePickerFiltro.setValue(null);
        hourSpinner.getValueFactory().setValue(0);
        minuteSpinner.getValueFactory().setValue(0);
        carregarTodasAvaliacoes();
    }
    private void configurarSpinners() {
        hourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
        minuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
    }
    private void mostrarAlerta(Alert.AlertType alertType, String titulo, String cabecalho, String mensagem) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
    @FXML void handleNavegarObras(ActionEvent event) { HelloApplication.telaDonoObra(); }
    @FXML void handleNavegarAvaliador(ActionEvent event) { HelloApplication.telaDonoAvaliador(); }
    @FXML void handleNavegarAutores(ActionEvent event) { HelloApplication.telaDono(); }
    @FXML void handleNavegarAvaliacoes(ActionEvent event) { HelloApplication.telaDonoAvaliacoes(); }
    @FXML void handleNavegarRelatorio(ActionEvent event) { HelloApplication.telaDonoRelatorio(); }
    @FXML void handleLogout(ActionEvent event) { HelloApplication.telaLogin(); }
}