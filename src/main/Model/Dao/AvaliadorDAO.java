package main.Model.Dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import main.Entities.Avaliador;
import main.Entities.Obra;
import main.Util.JPAUtil;

import java.util.List;

public class AvaliadorDAO extends AbstractDAO implements UserGenericInterDAO<Avaliador> {

    @Override
    public void salvar(Avaliador avaliador) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(avaliador);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao salvar Avaliador: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void atualizar(Avaliador avaliador) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(avaliador);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao atualizar Avaliador: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void deletar(Avaliador avaliador) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            // Garante que a entidade está no estado gerenciado antes da remoção
            if (!em.contains(avaliador)) {
                avaliador = em.merge(avaliador);
            }
            em.remove(avaliador);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao deletar Avaliador: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Avaliador buscarPorCPF(String cpf) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Avaliador> query = em.createQuery(
                    "SELECT a FROM Avaliador a WHERE a.cpf = :cpf", Avaliador.class);
            query.setParameter("cpf", cpf);
            List<Avaliador> resultado = query.setMaxResults(1).getResultList();
            // Retorna o primeiro resultado ou null se a lista estiver vazia
            return resultado.isEmpty() ? null : resultado.get(0);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Avaliador> buscarPorNome(String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // Busca por parte do nome usando LIKE
            TypedQuery<Avaliador> query = em.createQuery(
                    "SELECT a FROM Avaliador a WHERE a.nome LIKE :nome", Avaliador.class);
            query.setParameter("nome", "%" + nome + "%");
            return query.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Avaliador> BuscarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Avaliador> query = em.createQuery("SELECT a FROM Avaliador a", Avaliador.class);
            return query.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Busca o avaliador por uma obra sua
    public List<Avaliador> buscarPorObra(Obra obra) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Avaliador> query = em.createQuery(
                    "SELECT DISTINCT av.avaliadorObra FROM Avaliacoes av WHERE av.obraAvaliar = :obra", Avaliador.class);
            query.setParameter("obra", obra);
            return query.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}