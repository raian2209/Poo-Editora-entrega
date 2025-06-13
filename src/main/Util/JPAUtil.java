package main.Util;

import jakarta.persistence.*;

public class JPAUtil {

    private static final String PERSISTENCE_UNIT_NAME = "seuPU"; // Substitua por um nome significativo
    private static final EntityManagerFactory EMF = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
        try {
            // Cria o EntityManagerFactory a partir da unidade de persistência definida no persistence.xml
            return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Exception ex) {
            // Em um ambiente de produção, você deve usar um logger adequado
            System.err.println("Erro ao criar o EntityManagerFactory inicial." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return EMF;
    }

    // Método para obter um EntityManager para cada transação
    public static EntityManager getEntityManager() {
        return EMF.createEntityManager();
    }

    public static void shutdown() {
        if (EMF != null && EMF.isOpen()) {
            EMF.close();
        }
    }
}
