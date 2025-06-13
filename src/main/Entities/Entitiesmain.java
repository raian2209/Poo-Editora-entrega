package main.Entities;

import jakarta.persistence.EntityManager;
import main.Util.*;

public class Entitiesmain {


    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            Obra obra = new Obra("raimundo", "terror", 2000, "raimndo");


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
