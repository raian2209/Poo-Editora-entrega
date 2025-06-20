package main.Model.Dao;

import main.Entities.Escritor;
import main.Entities.Obra;
import java.util.List;

public class Daomain {
	

    public static void criarEscritores(){
        Escritor escritor1  = new Escritor("teste1","111111111","rua aaaaa");
        Escritor escritor2  = new Escritor("teste2","222222222","rua bbbbb");
        Escritor escritor3  = new Escritor("teste3","333333333","rua cccccc");

        EscritorDAO escritorDAO = new EscritorDAO();
        escritorDAO.salvar(escritor1);
        escritorDAO.salvar(escritor2);
        escritorDAO.salvar(escritor3);

    }

    public static void removerEscritores(){

        EscritorDAO escritorDAO = new EscritorDAO();
        escritorDAO.deletar(escritorDAO.buscarPorCPF("111111111"));

    }

    public static void buscarEscritores(){
        EscritorDAO escritorDAO = new EscritorDAO();
        for ( Escritor e: escritorDAO.BuscarTodos()){
            System.out.println(e.toString());
        }
        System.out.println(escritorDAO.buscarPorCPF("222222222"));
        System.out.println(escritorDAO.buscarPorNome("teste3"));
    }

    public static void atualizarEscritores(){

        EscritorDAO escritorDAO = new EscritorDAO();
        Escritor escritor = escritorDAO.buscarPorCPF("222222222");
        escritor.setEndereco("Rua");
        escritorDAO.atualizar(escritor);
    }
    //-----------------------------------------------------------------
    
    public static void criarObras() {
        EscritorDAO escritorDAO = new EscritorDAO();
        Escritor escritor = new Escritor("Autor Teste", "999999999", "Rua XPTO");
        escritorDAO.salvar(escritor);

        Obra obra1 = new Obra("Obra A", "Ficção", 2001, escritor);
        Obra obra2 = new Obra("Obra B", "Terror", 2005, escritor);
        Obra obra3 = new Obra("Outra Obra", "Drama", 2005, escritor);

        ObraDAO obraDAO = new ObraDAO();
        obraDAO.salvar(obra1);
        obraDAO.salvar(obra2);
        obraDAO.salvar(obra3);

        System.out.println("Obras criadas e salvas.");
    }

    public static void buscarObras() {
        ObraDAO obraDAO = new ObraDAO();
        EscritorDAO escritorDAO = new EscritorDAO();

        System.out.println("Todas as obras:");
        List<Obra> todas = obraDAO.BuscarTodos();
        for (Obra o : todas) {
            System.out.println(o);
        }

        System.out.println("Obras com título contendo 'Obra':");
        List<Obra> porTitulo = obraDAO.buscarPorTitulo("Obra");
        for (Obra o : porTitulo) {
            System.out.println(o);
        }

        System.out.println("Obras do ano 2005:");
        List<Obra> porAno = obraDAO.buscarPorAno(2005);
        for (Obra o : porAno) {
            System.out.println(o);
        }

        Escritor escritor = escritorDAO.buscarPorCPF("999999999");
        System.out.println("Obras do escritor:");
        List<Obra> porEscritor = obraDAO.buscarPorEscritor(escritor);
        for (Obra o : porEscritor) {
            System.out.println(o);
        }
    }

    public static void atualizarObra() {
        ObraDAO obraDAO = new ObraDAO();
        List<Obra> obras = obraDAO.buscarPorTitulo("Obra B");

        if (!obras.isEmpty()) {
            Obra obra = obras.get(0);
            obra.setGenero("Suspense");
            obra.setAno(2010);
            obraDAO.atualizar(obra);
            System.out.println("Obra atualizada.");
        } else {
            System.out.println("Obra não encontrada para atualização.");
        }
    }

    public static void removerObras() {
        ObraDAO obraDAO = new ObraDAO();
        obraDAO.removerPorTitulo("Obra A");
        System.out.println("Obra A removida.");
    }
    
    //-----------------------------------------------------------------
    public static void main(String[] args) {
        criarEscritores();
        removerEscritores();
        buscarEscritores();
        atualizarEscritores();
        System.out.println("--------------------------------------------");
        criarObras();
        buscarObras();
        atualizarObra();
        removerObras();
        buscarObras();
    }
}
