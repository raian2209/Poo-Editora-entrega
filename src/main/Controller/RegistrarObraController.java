package main.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.Entities.Escritor;
import main.Entities.Obra;
import main.Exceptions.CPFInvalidoException;
import main.Exceptions.CamposVaziosException;
import main.Exceptions.NumeroInvalidoExeption;
import main.Exceptions.TituloDigitadoJaExistente;
import main.Model.Builders.*;
import main.Model.Service.EscritorService;
import main.Model.Service.ObraService;

import java.net.URL;
import java.util.ResourceBundle;
//----------------LEMBRAR DE TIRAR OS ESCRITORES CRIADOS PRA TESTE--------------------------------//
public class RegistrarObraController implements Initializable {
    ObraService obraService = new  ObraService();
    EscritorService escritorService = new EscritorService();

    @FXML
    private TextField ano;

    @FXML
    private TextField cpfDoAutor;

    @FXML
    private TextField genero;

    @FXML
    private Button registrar;

    @FXML
    private TextField titulo;

    @FXML
    private ComboBox<String> seletorGenero;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Como não tem nenhum escritor registrado no BD, criei esses dois.
        Escritor alisson = new Escritor("Alisson", "104.404.303.32", "Rua dois");
        Escritor neymar = new Escritor("Neymar", "104.404.503.32", "Rua tres");

        //o programa ja se inicia com esses dois escritores
        escritorService.salvar(alisson);
        escritorService.salvar(neymar);

        seletorGenero.getItems().addAll(
                "Aventura",
                "Romance",
                "Terror",
                "Suspense",
                "Outro..."
        );

        seletorGenero.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.equals("Outro...")) {
                    genero.setEditable(true);
                    genero.clear();
                    genero.requestFocus();
                } else {
                    genero.setText(newValue);
                    genero.setEditable(false);
                }
            }
        });
    }

    @FXML
    void registrarObra(ActionEvent event) {
        try {
            if (titulo.getText().isEmpty() || genero.getText().isEmpty() ||
                    ano.getText().isEmpty() || cpfDoAutor.getText().isEmpty()) {
                throw new CamposVaziosException("Todos os campos devem ser preenchidos.");
            }

            int anoDaObra;
            try {
                anoDaObra = Integer.parseInt(ano.getText());
            } catch (NumberFormatException e) {
                throw new NumeroInvalidoExeption("O campo ano deve receber um número inteiro!");
            }

            Escritor autorEncontrado = escritorService.buscarPorCPF(cpfDoAutor.getText());
            if (autorEncontrado == null) {
                throw new CPFInvalidoException("Digite o CPF de um autor cadastrado!");
            }

            //---

            Obra novaObra; // A variável que vai receber a obra finalizada
            String generoSelecionado = genero.getText();

            ObraBuilder builder = new ConcreteObraBuilder();
            ObraDirector director = new ObraDirector();

            switch (generoSelecionado) {
                case "Terror":
                    director.constructTerror(builder, titulo.getText(), autorEncontrado, anoDaObra);
                    novaObra = builder.getResult();
                    break;

                case "Suspense":
                    director.constructSuspense(builder, titulo.getText(), autorEncontrado, anoDaObra);
                    novaObra = builder.getResult();
                    break;

                case "Fantasia":
                    director.constructFantasia(builder, titulo.getText(), autorEncontrado, anoDaObra);
                    novaObra = builder.getResult();
                    break;

                case "Romance":
                    director.constructRomance(builder, titulo.getText(), autorEncontrado, anoDaObra);
                    novaObra = builder.getResult();
                    break;

                default:
                    // Se não for nenhum dos gêneros pré prontos, usa o build comum
                    novaObra = new ConcreteObraBuilder()
                            .buildTitulo(titulo.getText())
                            .buildGenero(generoSelecionado) // Usa o gênero que foi digitado
                            .buildAno(anoDaObra)
                            .buildAutor(autorEncontrado)
                            .getResult();
                    break;
            }

            //--------------------------------------------
            if (obraService.buscarPorTitulo(novaObra.getTitulo()).isEmpty()) {
                obraService.salvar(novaObra);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Obra registrada!", "Obra registrada com sucesso!");
                titulo.clear();
                genero.clear();
                ano.clear();
                cpfDoAutor.clear();
                genero.setEditable(true);
            } else {
                throw new TituloDigitadoJaExistente("Já foi cadastrada uma obra com esse título!");
            }

        } catch (CamposVaziosException e) {
            System.out.println(e.getMessage());
            mostrarAlerta(Alert.AlertType.ERROR, "Atenção", "Campos vazios!", e.getMessage());
        } catch (NumeroInvalidoExeption e) {
            System.out.println(e.getMessage());
            mostrarAlerta(Alert.AlertType.ERROR, "Atenção", "Ano inválido!", e.getMessage());
        } catch (CPFInvalidoException e) {
            System.out.println(e.getMessage());
            mostrarAlerta(Alert.AlertType.ERROR, "Atenção", "CPF inválido!", e.getMessage());
        } catch (TituloDigitadoJaExistente e) {
            System.out.println(e.getMessage());
            mostrarAlerta(Alert.AlertType.WARNING, "Atenção", "Obra já existente!", e.getMessage());
        }
    }

    private void mostrarAlerta(Alert.AlertType alertType,String titulo, String cabecalho, String mensagem) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
