package main.Entities;

import jakarta.persistence.EntityManager;
import main.Util.JPAUtil;

public class Entitiesmain {


    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Escritor escritor = new Escritor("raimundo","1.1.1.1", "caralha");



            em.persist(escritor);
            em.getTransaction().commit();
            System.out.println("Escritor salvo com sucesso!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao salvar Escritor: " + e.getMessage());
        } finally {
            em.close();
            JPAUtil.shutdown();
        }

        try {
            em.getTransaction().begin();

            Obra obra = new Obra("raimundo", "terror", 2000);


            em.persist(obra);
            em.getTransaction().commit();
            System.out.println("Usuário salvo com sucesso!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao salvar usuário: " + e.getMessage());
        } finally {
            em.close();
            JPAUtil.shutdown();
        }
    }
}
