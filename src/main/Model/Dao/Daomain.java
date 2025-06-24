package main.Model.Dao;

import main.Entities.Escritor;
import main.Entities.Obra;
import main.Entities.Avaliador;
import main.Entities.Avaliacoes;

import java.util.List;

public class Daomain {

    //Escritores
    public static void criarEscritores() {
        Escritor escritor1 = new Escritor("teste1", "111111111", "rua aaaaa");
        Escritor escritor2 = new Escritor("teste2", "222222222", "rua bbbbb");
        Escritor escritor3 = new Escritor("teste3", "333333333", "rua cccccc");

        EscritorDAO escritorDAO = new EscritorDAO();
        escritorDAO.salvar(escritor1);
        escritorDAO.salvar(escritor2);
        escritorDAO.salvar(escritor3);
    }

    public static void removerEscritores() {
        EscritorDAO escritorDAO = new EscritorDAO();
        Escritor escritor = escritorDAO.buscarPorCPF("111111111");
        if (escritor != null) {
            escritorDAO.deletar(escritor);
        }
    }

    public static void buscarEscritores() {
        EscritorDAO escritorDAO = new EscritorDAO();
        for (Escritor e : escritorDAO.BuscarTodos()) {
            System.out.println(e.toString());
        }
        for (Escritor e : escritorDAO.BuscarTodos()) {
            System.out.println(e.toString());
        }
        System.out.println(escritorDAO.buscarPorCPF("222222222"));
        System.out.println(escritorDAO.buscarPorNome("teste3"));
    }

    public static void atualizarEscritores() {
        EscritorDAO escritorDAO = new EscritorDAO();
        Escritor escritor = escritorDAO.buscarPorCPF("222222222");
        if (escritor != null) {
            escritor.setEndereco("Rua");
            escritorDAO.atualizar(escritor);
        }
    }

    //Obras
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
        for (Obra o : obraDAO.BuscarTodos()) {
            System.out.println(o.getTitulo());
        }

        System.out.println("Obras com título contendo 'Obra':");
        for (Obra o : obraDAO.buscarPorTitulo("Obra")) {
            System.out.println(o.getTitulo());
        }

        System.out.println("Obras do ano 2005:");
        for (Obra o : obraDAO.buscarPorAno(2005)) {
            System.out.println(o.getAno());
        }

        Escritor escritor = escritorDAO.buscarPorCPF("999999999");
        if (escritor != null) {
            System.out.println("Obras do escritor:");
            for (Obra o : obraDAO.buscarPorEscritor(escritor)) {
                System.out.println(o.getTitulo());
            }
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

    //Avaliadores
    public static void criarAvaliadores() {
        Avaliador avaliador1 = new Avaliador("Avaliador Um", "123123123", "Rua do Avaliador 1");
        Avaliador avaliador2 = new Avaliador("Avaliador Dois", "321321321", "Rua do Avaliador 2");

        AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
        avaliadorDAO.salvar(avaliador1);
        avaliadorDAO.salvar(avaliador2);

        System.out.println("\nAvaliadores de teste criados e salvos.");
    }

    public static void buscarAvaliadores() {
        AvaliadorDAO dao = new AvaliadorDAO();

        System.out.println("\nTodos os avaliadores:");
        for (Avaliador a : dao.BuscarTodos()) {
            System.out.println(a);
        }

        System.out.println("\nBuscando avaliador por CPF '123123123':");
        System.out.println(dao.buscarPorCPF("123123123"));

        System.out.println("\nBuscando avaliador por nome 'Dois':");
        for (Avaliador a : dao.buscarPorNome("Dois")) {
            System.out.println(a);
        }
    }

    public static void atualizarAvaliador() {
        AvaliadorDAO dao = new AvaliadorDAO();
        Avaliador a = dao.buscarPorCPF("123123123");
        if (a != null) {
            a.setEndereco("Novo Endereço 456");
            dao.atualizar(a);
            System.out.println("\nAvaliador atualizado.");
        }
    }

    public static void deletarAvaliador() {
        AvaliadorDAO dao = new AvaliadorDAO();
        Avaliador a = dao.buscarPorCPF("321321321");
        if (a != null) {
            dao.deletar(a);
            System.out.println("\nAvaliador deletado.");
        }
    }

    //Avaliacoes
    public static void criarAvaliacaoParaObra() {
        ObraDAO obraDAO = new ObraDAO();
        AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
        AvaliacaoDAO avaliacoesDAO = new AvaliacaoDAO();

        List<Obra> obras = obraDAO.buscarPorTitulo("Obra B");
        Avaliador avaliador = avaliadorDAO.buscarPorCPF("123123123");

        if (!obras.isEmpty() && avaliador != null) {
            Avaliacoes avaliacao = new Avaliacoes(avaliador, obras.get(0));
            avaliacoesDAO.salvar(avaliacao);
            System.out.println("\nAvaliação criada e salva.");
        } else {
            System.out.println("\nNão foi possível criar avaliação: obra ou avaliador não encontrado.");
        }
    }

    public static void buscarAvaliadoresPorObra() {
        ObraDAO obraDAO = new ObraDAO();
        AvaliadorDAO avaliadorDAO = new AvaliadorDAO();

        List<Obra> obras = obraDAO.buscarPorTitulo("Obra B");
        if (!obras.isEmpty()){
            Obra obra = obras.get(0);
            List<Avaliador> avaliadores = avaliadorDAO.buscarPorObra(obra);

            System.out.println("\nAvaliadores que avaliaram a obra '" + obra.getTitulo() + "':");
            for (Avaliador a : avaliadores) {
                System.out.println(a);
            }
        }
    }

    public static void testarAvaliacoes() {
        AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        ObraDAO obraDAO = new ObraDAO();

        Avaliador av1 = new Avaliador("Avaliador Extra 1", "999888777", "Endereço Extra 1");
        Avaliador av2 = new Avaliador("Avaliador Extra 2", "888777666", "Endereço Extra 2");
        avaliadorDAO.salvar(av1);
        avaliadorDAO.salvar(av2);

        List<Obra> obras = obraDAO.buscarPorTitulo("Obra B");
        if (!obras.isEmpty()) {
            Obra obra = obras.get(0);

            Avaliacoes av1Obra = new Avaliacoes(av1, obra);
            Avaliacoes av2Obra = new Avaliacoes(av2, obra);

            avaliacaoDAO.salvar(av1Obra);
            avaliacaoDAO.salvar(av2Obra);

            System.out.println("\nTodas as avaliações salvas:");
            for (Avaliacoes a : avaliacaoDAO.BuscarTodos()) {
                System.out.println(a);
            }

            System.out.println("\n-Avaliações feitas por 'Avaliador Extra 1':");
            for (Avaliacoes a : avaliacaoDAO.buscarPorAvaliador(av1)) {
                System.out.println(a);
            }
        }
    }


    public static void main(String[] args) {
        criarEscritores();
        removerEscritores();
        buscarEscritores();
        atualizarEscritores();

        criarObras();
        buscarObras();
        atualizarObra();
        removerObras();
        buscarObras();

        criarAvaliadores();
        buscarAvaliadores();
        atualizarAvaliador();
        deletarAvaliador();

        criarAvaliacaoParaObra();
        buscarAvaliadoresPorObra();
        testarAvaliacoes();
    }
}