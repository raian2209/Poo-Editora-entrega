package main.Controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import main.Model.Service.*;
import main.Entities.*;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;


import java.util.ResourceBundle;

public class ListViewAvaliacoesController implements Initializable {

    @FXML
    private Button botaoVoltar;

    @FXML
    private ListView<Obra> listViewObrasAvaliadas;

    private List<Obra> obrasAvaliadas = new ArrayList<>();

    private ObservableList<Obra> obsListViewObrasAvaliadas;

    public void initialize(URL URL, ResourceBundle rb) {
        carregarCategorias();
    }

    public void carregarCategorias(){
        Escritor alisson = new Escritor("Alisson", "104.404.303.32", "Rua dois");
        Escritor neymar = new Escritor("Neymar", "104.404.503.32", "Rua tres");
        EscritorService escritorService = new EscritorService();
        escritorService.salvar(alisson);
        escritorService.salvar(neymar);
        Obra obra1 = new Obra("Harry porrer", "ficção", 2010, alisson);
        Obra obra2 = new Obra("Futebol", "fut", 2020, neymar);
        Obra obra3 = new Obra("Programação", "estudos", 2030, alisson);
        Obra obra4 = new Obra("Título", "genero", 1999, neymar);
        Obra obra5 = new Obra("Harry porrer", "ficção", 2010, alisson);
        Obra obra6 = new Obra("Futebol", "fut", 2020, neymar);
        Obra obra7 = new Obra("Programação", "estudos", 2030, alisson);
        Obra obra8 = new Obra("Título", "genero", 1999, neymar);
        Obra obra9 = new Obra("Harry porrer", "ficção", 2010, alisson);
        Obra obra10 = new Obra("Futebol", "fut", 2020, neymar);
        Obra obra11 = new Obra("Programação", "estudos", 2030, alisson);
        Obra obra12 = new Obra("Título", "genero", 1999, neymar);
        ObraService obraService = new ObraService();
        obraService.salvar(obra1);
        obraService.salvar(obra2);
        obraService.salvar(obra3);
        obraService.salvar(obra4);
        obraService.salvar(obra5);
        obraService.salvar(obra6);
        obraService.salvar(obra7);
        obraService.salvar(obra8);
        obraService.salvar(obra9);
        obraService.salvar(obra10);
        obraService.salvar(obra11);
        obraService.salvar(obra12);
        Obra obra13 = new Obra("Livro A", "genero A", 2001, alisson);
        Obra obra14 = new Obra("Livro B", "genero B", 2002, neymar);
        Obra obra15 = new Obra("Livro C", "genero C", 2003, alisson);
        Obra obra16 = new Obra("Livro D", "genero D", 2004, neymar);
        Obra obra17 = new Obra("Livro E", "genero E", 2005, alisson);
        Obra obra18 = new Obra("Livro F", "genero F", 2006, neymar);
        Obra obra19 = new Obra("Livro G", "genero G", 2007, alisson);
        Obra obra20 = new Obra("Livro H", "genero H", 2008, neymar);
        Obra obra21 = new Obra("Livro I", "genero I", 2009, alisson);
        Obra obra22 = new Obra("Livro J", "genero J", 2010, neymar);
        Obra obra23 = new Obra("Livro K", "genero K", 2011, alisson);
        Obra obra24 = new Obra("Livro L", "genero L", 2012, neymar);
        Obra obra25 = new Obra("Livro M", "genero M", 2013, alisson);
        Obra obra26 = new Obra("Livro N", "genero N", 2014, neymar);
        Obra obra27 = new Obra("Livro O", "genero O", 2015, alisson);
        Obra obra28 = new Obra("Livro P", "genero P", 2016, neymar);
        Obra obra29 = new Obra("Livro Q", "genero Q", 2017, alisson);
        Obra obra30 = new Obra("Livro R", "genero R", 2018, neymar);
        Obra obra31 = new Obra("Livro S", "genero S", 2019, alisson);
        Obra obra32 = new Obra("Livro T", "genero T", 2020, neymar);
        Obra obra33 = new Obra("Livro U", "genero U", 2021, alisson);
        Obra obra34 = new Obra("Livro V", "genero V", 2022, neymar);

        obraService.salvar(obra13);
        obraService.salvar(obra14);
        obraService.salvar(obra15);
        obraService.salvar(obra16);
        obraService.salvar(obra17);
        obraService.salvar(obra18);
        obraService.salvar(obra19);
        obraService.salvar(obra20);
        obraService.salvar(obra21);
        obraService.salvar(obra22);
        obraService.salvar(obra23);
        obraService.salvar(obra24);
        obraService.salvar(obra25);
        obraService.salvar(obra26);
        obraService.salvar(obra27);
        obraService.salvar(obra28);
        obraService.salvar(obra29);
        obraService.salvar(obra30);
        obraService.salvar(obra31);
        obraService.salvar(obra32);
        obraService.salvar(obra33);
        obraService.salvar(obra34);


        obsListViewObrasAvaliadas = FXCollections.observableArrayList(obraService.BuscarTodos());

        listViewObrasAvaliadas.setItems(obsListViewObrasAvaliadas);

    }

}
