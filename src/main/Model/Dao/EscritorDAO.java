package main.Model.Dao;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import main.Entities.Escritor;
import main.Entities.Obra;
import main.Util.JPAUtil;

import java.util.List;

public class EscritorDAO extends AbstractDAO implements UserGenericInterDAO<Escritor>{


    @Override
    public Escritor buscarPorCPF(String cpf) {
            TypedQuery<Escritor> query = em.createQuery(
                    "SELECT e FROM Escritor e WHERE e.cpf = :cpf", Escritor.class);
            query.setParameter("cpf", cpf);

            List<Escritor> resultado = query.setMaxResults(1).getResultList();
            return resultado.isEmpty() ? null : resultado.get(0);

    }

    @Override
    public void salvar(Escritor escritor) {
        em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(escritor);
            em.getTransaction().commit();
        }  catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // só faz rollback se ainda puder
            }
            System.err.println("Erro ao salvar Escritor: " + e.getMessage());
        }finally {
            em.close();
        }
    }

    @Override
    public void atualizar(Escritor escritor) {
        try {
            em.getTransaction().begin();
            em.merge(escritor);
            em.getTransaction().commit();
        }  catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao atualisar Escritor: " + e.getMessage());
        }finally {
            em.close();
        }
    }

    @Override
    public List<Escritor> BuscarTodos() {
        TypedQuery<Escritor> query = em.createQuery(
                "SELECT e FROM Escritor e", Escritor.class);
        return query.getResultList();
    }

    @Override
    public void deletar(Escritor entidade) {
        try {
            em.getTransaction().begin();
            if (entidade != null) {
                em.remove(entidade); // remove da base
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }


    public Escritor buscarPorObra(Obra obra) {
        TypedQuery<Escritor> query = em.createQuery(
                "SELECT o.autor FROM Obra o WHERE o.id = :idObra", Escritor.class);
        query.setParameter("idObra", 1L);
        return query.getSingleResult();
    }


    @Override
    public List<Escritor> buscarPorNome(String nome) {
        TypedQuery<Escritor> query = em.createQuery(
                "SELECT e FROM Escritor e WHERE e.nome = :nome", Escritor.class);
        query.setParameter("nome", nome);
        return  query.getResultList();
    }
}
