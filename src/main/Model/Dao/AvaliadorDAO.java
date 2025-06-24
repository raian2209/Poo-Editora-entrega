package main.Model.Dao;

import jakarta.persistence.TypedQuery;
import main.Entities.Avaliador;
import main.Entities.Escritor;
import main.Entities.Obra;
import main.Util.JPAUtil;

import java.util.List;

public class AvaliadorDAO extends AbstractDAO implements UserGenericInterDAO<Avaliador> {

    @Override
    public void salvar(Avaliador avaliador) {
        em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(avaliador);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao salvar Avaliador: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public void atualizar(Avaliador avaliador) {
        em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(avaliador);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao atualizar Avaliador: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public void deletar(Avaliador avaliador) {
        em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Escritor gerenciado = em.find(Escritor.class, avaliador.getId());
            if (avaliador != null && em.contains(avaliador)){
                if(gerenciado !=null) {
                    em.remove(gerenciado);// remove da base
                } // remove da base
        }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao deletar Avaliador: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Avaliador buscarPorCPF(String cpf) {
        em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Avaliador> query = em.createQuery(
                    "SELECT a FROM Avaliador a WHERE a.cpf = :cpf", Avaliador.class);
            query.setParameter("cpf", cpf);
            List<Avaliador> resultado = query.setMaxResults(1).getResultList();
            return resultado.isEmpty() ? null : resultado.get(0);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Avaliador> buscarPorNome(String nome) {
        em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Avaliador> query = em.createQuery(
                    "SELECT a FROM Avaliador a WHERE a.nome LIKE :nome", Avaliador.class);
            query.setParameter("nome", "%" + nome + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Avaliador> BuscarTodos() {
        em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Avaliador> query = em.createQuery(
                    "SELECT a FROM Avaliador a", Avaliador.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Avaliador> buscarPorObra(Obra obra) {
        em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Avaliador> query = em.createQuery(
                    "SELECT DISTINCT av.avaliadorObra FROM Avaliacoes av WHERE av.obraAvaliar = :obra", Avaliador.class);
            query.setParameter("obra", obra);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
