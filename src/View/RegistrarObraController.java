package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.Entities.Escritor;
import main.Entities.Obra;
import main.Exceptions.CPFInvalidoException;
import main.Exceptions.CamposVaziosException;
import main.Exceptions.NumeroInvalidoExeption;
import main.Model.Dao.ObraDAO;
import main.Model.Service.EscritorService;
import main.Model.Service.ObraService;

import java.net.URL;
import java.util.ResourceBundle;

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
    private Label aviso;

    @FXML
    private Label obraJaCaddastrada;

    @FXML
    private Label obraRegistrada;

    @FXML
    private Label camposVazios;

    @FXML
    private Label anoInvalido;

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
            //Limpar os labels de alterta

            if(anoInvalido.isVisible()){
                anoInvalido.setVisible(false);
            }
            if(obraRegistrada.isVisible()){
                obraRegistrada.setVisible(false);
            }
            if(camposVazios.isVisible()){
                camposVazios.setVisible(false);
            }
            if(aviso.isVisible()){
                aviso.setVisible(false);
            }
            if(obraJaCaddastrada.isVisible()){
                obraJaCaddastrada.setVisible(false);
            }
            //----------------------

            // Verificando se não ha campos vazios. Se tiver algum vazio, dispara a exceção
            if (titulo.getText().isEmpty() || genero.getText().isEmpty() ||
                    ano.getText().isEmpty() || cpfDoAutor.getText().isEmpty()) {
                throw new CamposVaziosException("Todos os campos devem ser preenchidos.");
            }

            try{
                obra.setAno(Integer.parseInt(ano.getText()));
            }catch(NumberFormatException e){
                throw new NumeroInvalidoExeption("O campo ano deve receber um número inteiro.");
            }

            // Atribuindo valores digitados à obra criada

            obra.setGenero(genero.getText());
            obra.setTitulo(titulo.getText());
            // Talvez seria melhor armaenar o cpd digitado em uma String
            if(escritorService.buscarPorCPF(cpfDoAutor.getText()) == null){
                throw new CPFInvalidoException("CPF inválido.");
            }
            obra.setAutor(escritorService.buscarPorCPF(cpfDoAutor.getText()));

            // Verificação0 se já existe obras com o nome digitado

            if(obraService.buscarPorTitulo(titulo.getText()).isEmpty()){
                obraService.salvar(obra);
                obraRegistrada.setVisible(true);
            }
            else{
                obraJaCaddastrada.setVisible(true);
            }

        }catch(CamposVaziosException e){
            System.out.println(e.getMessage());
            camposVazios.setVisible(true);
        }catch(NumeroInvalidoExeption e){
            System.out.println(e.getMessage());
            anoInvalido.setVisible(true);
        }catch(CPFInvalidoException e){
            aviso.setVisible(true);
            System.out.println(e.getMessage());
        }
    }



}
