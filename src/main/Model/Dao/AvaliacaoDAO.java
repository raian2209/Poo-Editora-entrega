package main.Model.Dao;

import jakarta.persistence.TypedQuery;
import main.Entities.Avaliacoes;
import main.Entities.Avaliador;
import main.Entities.Status;
import main.Util.JPAUtil;

import java.time.LocalDateTime;
import java.util.List;

public class AvaliacaoDAO extends AbstractDAO implements AvaliacaoInterDAO<Avaliacoes> {

    @Override
    public void salvar(Avaliacoes entidade) {
        em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entidade);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao salvar Avaliacao: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public void atualizar(Avaliacoes entidade) {
        em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entidade);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao atualizar Avaliacao: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Avaliacoes> BuscarTodos() {
        em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Avaliacoes> query = em.createQuery("SELECT a FROM Avaliacoes a", Avaliacoes.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void deletar(Avaliacoes entidade) {
        em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();


            if (entidade != null && em.contains(entidade))em.remove(entidade);

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao deletar Avaliacao: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Avaliacoes> buscarPorAvaliador(Avaliador avaliador) {
        em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Avaliacoes> query = em.createQuery(
                    "SELECT a FROM Avaliacoes a WHERE a.avaliadorObra = :avaliador", Avaliacoes.class);
            query.setParameter("avaliador", avaliador);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Avaliacoes buscarPorId(Long ID) {
        em = JPAUtil.getEntityManager();
        try {
            return em.find(Avaliacoes.class, ID);
        } finally {
            em.close();
        }
    }

    public List<Avaliacoes> buscarAposDataComStatusAvaliado(LocalDateTime dataLimite) {
        em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Avaliacoes> query = em.createQuery(
                    "SELECT a FROM Avaliacoes a WHERE a.dataAvaliacao > :data AND a.status <> :status", Avaliacoes.class);
            query.setParameter("data", dataLimite);
            query.setParameter("status", Status.PADRAO);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Avaliacoes> buscarPorAvaliadorComStatusAvaliado(Avaliador avaliador) {
        em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Avaliacoes> query = em.createQuery(
                    "SELECT a FROM Avaliacoes a WHERE a.avaliadorObra = :avaliadorObra AND a.status <> :status", Avaliacoes.class);
            query.setParameter("avaliadorObra", avaliador);
            query.setParameter("status", Status.PADRAO);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Avaliacoes> buscarAposDataComStatusNaoAvaliado(LocalDateTime dataLimite) {
        em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Avaliacoes> query = em.createQuery(
                    "SELECT a FROM Avaliacoes a WHERE a.dataAvaliacao > :data AND a.status = :status", Avaliacoes.class);
            query.setParameter("data", dataLimite);
            query.setParameter("status", Status.PADRAO);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Avaliacoes> buscarAposData(LocalDateTime dataLimite) {
        em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Avaliacoes> query = em.createQuery(
                    "SELECT a FROM Avaliacoes a WHERE a.dataAvaliacao > :data", Avaliacoes.class);
            query.setParameter("data", dataLimite);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Avaliacoes> buscarPorData(LocalDateTime data) {
        em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Avaliacoes> query = em.createQuery(
                    "SELECT a FROM Avaliacoes a WHERE a.dataAvaliacao = :data", Avaliacoes.class);
            query.setParameter("data", data);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
