package main.Entities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import main.Util.JPAUtil;

import java.time.LocalDateTime;
import java.util.List;

public class Entitiesmain {

    public static void registrarDono(){

        Dono dono = new Dono("popopopo", "33333333", "Rio de Janeiro");
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(dono);  // Primeiro salvamos o dono
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao salvar Dono: " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public static void registrarAvaliador(){

        Avaliador avaliador = new Avaliador("raimundo", "222222222", "Rio de Janeiro");
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(avaliador);  // Primeiro salvamos o avaliador
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao salvar Avaliador: " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public static void registrarEscritor(){

        Escritor escritor = new Escritor("Machado de Assis", "11111111111", "Rio de Janeiro");
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(escritor);  // Primeiro salvamos o escritor
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao salvar Escritor: " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public static void registrarObra(){
        EntityManager em = JPAUtil.getEntityManager();

        try {
            // Primeiro, buscarmos o escritor já salvo
            Escritor escritor = em.find(Escritor.class, 3L); // ID real do escritor salvo antes
            System.out.println( escritor.toString());
            Obra obra = new Obra();
            obra.setTitulo("Dom Casmurro");
            obra.setGenero("Romance");
            obra.setAutor(escritor); // associação

            em.getTransaction().begin();
            em.persist(obra); // persistimos a obra com o escritor já vinculado
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao salvar Obra: " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public static void registrarAvaliacao(){
        EntityManager em = JPAUtil.getEntityManager();

        try {


            // Buscar Avaliador e Obra já existentes no banco
            Avaliador avaliador = em.find(Avaliador.class, 1L);
            Obra obra = em.find(Obra.class, 1L);
//            System.out.println( avaliador.toString() );
            // Criar Avaliação
            Avaliacoes avaliacao = new Avaliacoes(avaliador,obra);

            em.getTransaction().begin();

            // Persistir
            em.persist(avaliacao);

            em.getTransaction().commit();
        }catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao salvar Avaliação: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        Entitiesmain.registrarAvaliador();
        Entitiesmain.registrarDono();
        Entitiesmain.registrarEscritor();
        Entitiesmain.registrarObra();
        Entitiesmain.registrarAvaliacao();
    }
}
