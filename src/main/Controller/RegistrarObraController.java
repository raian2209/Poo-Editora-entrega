package main.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.Entities.Escritor;
import main.Entities.Obra;
import main.Exceptions.CPFInvalidoException;
import main.Exceptions.CamposVaziosException;
import main.Exceptions.NumeroInvalidoExeption;
import main.Exceptions.TituloDigitadoJaExistente;
import main.Model.Service.EscritorService;
import main.Model.Service.ObraService;

import java.net.URL;
import java.util.ResourceBundle;
//-----------------------Alteracoes
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


    public void initialize(URL url, ResourceBundle rb) {
        //Como não tem nenhum escritor registrado no BD, criei esses dois.
        Escritor alisson = new Escritor("Alisson", "104.404.303.32", "Rua dois");
        Escritor neymar = new Escritor("Neymar", "104.404.503.32", "Rua tres");

        //o programa ja se inicia com esses dois escritores
        escritorService.salvar(alisson);
        escritorService.salvar(neymar);
    }

    @FXML
    void registrarObra(ActionEvent event) {
        Obra obra = new Obra();
        try {
            //----------------------

            // Verificando se não ha campos vazios. Se tiver algum vazio, dispara a exceção
            if (titulo.getText().isEmpty() || genero.getText().isEmpty() ||
                    ano.getText().isEmpty() || cpfDoAutor.getText().isEmpty()) {
                throw new CamposVaziosException("Todos os campos devem ser preenchidos.");
            }

            try{
                obra.setAno(Integer.parseInt(ano.getText()));
            }catch(NumberFormatException e){
                throw new NumeroInvalidoExeption("O campo ano deve receber um número inteiro!");
            }

            // Atribuindo valores digitados à obra criada

            obra.setGenero(genero.getText());
            obra.setTitulo(titulo.getText());
            // Talvez seria melhor armaenar o cpd digitado em uma String
            if(escritorService.buscarPorCPF(cpfDoAutor.getText()) == null){
                throw new CPFInvalidoException("Digite o CPF de um autor cadastrado!");
            }
            obra.setAutor(escritorService.buscarPorCPF(cpfDoAutor.getText()));

            // Verificação0 se já existe obras com o nome digitado

            if(obraService.buscarPorTitulo(titulo.getText()).isEmpty()){
                obraService.salvar(obra);
                mostrarAlerta(Alert.AlertType.INFORMATION,"Sucesso","Obra registrada!", "Obra registrada com sucesso!");
                titulo.clear();
                genero.clear();
                ano.clear();
                cpfDoAutor.clear();
            }
            else{
                throw new TituloDigitadoJaExistente("Já foi cadastrada uma obra com esse título!");
            }

        }catch(CamposVaziosException e){
            System.out.println(e.getMessage());
            mostrarAlerta(Alert.AlertType.ERROR, "Atenção","Campos vazios!", e.getMessage());
        }catch(NumeroInvalidoExeption e){
            System.out.println(e.getMessage());
            mostrarAlerta(Alert.AlertType.ERROR, "Atenção","Ano inválido!", e.getMessage());
        }catch(CPFInvalidoException e){
            System.out.println(e.getMessage());
            mostrarAlerta(Alert.AlertType.ERROR, "Atenção","CPF inválido!", e.getMessage());
        }catch(TituloDigitadoJaExistente e){
            System.out.println(e.getMessage());
            mostrarAlerta(Alert.AlertType.WARNING,"Atenção","Obra já existente!", e.getMessage());
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
