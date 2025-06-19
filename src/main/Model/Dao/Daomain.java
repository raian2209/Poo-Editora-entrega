package main.Model.Dao;

import main.Entities.Escritor;

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

    public static void main(String[] args) {
        criarEscritores();
        removerEscritores();
        buscarEscritores();
        atualizarEscritores();
    }
}
