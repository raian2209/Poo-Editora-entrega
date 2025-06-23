package main.Model.Dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import main.Entities.Avaliacoes;
import main.Entities.Avaliador;
import main.Entities.Status;
import main.Util.JPAUtil;

import java.time.LocalDateTime;
import java.util.List;

public class AvaliacaoDAO extends AbstractDAO implements AvaliacaoInterDAO<Avaliacoes> {

    // Salva uma nova avaliação no banco
    @Override
    public void salvar(Avaliacoes entidade) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entidade);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao salvar Avaliacao: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Atualiza uma avaliação existente no banco
    @Override
    public void atualizar(Avaliacoes entidade) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entidade);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao atualizar Avaliacao: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Busca e retorna todas as avaliações
    @Override
    public List<Avaliacoes> BuscarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Avaliacoes> query = em.createQuery("SELECT a FROM Avaliacoes a", Avaliacoes.class);
            return query.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Deleta uma avaliação do banco
    @Override
    public void deletar(Avaliacoes entidade) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Avaliacoes a = em.find(Avaliacoes.class, entidade.getId());
            if (a != null) {
                em.remove(a);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao deletar Avaliacao: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Busca avaliações por um avaliador específico
    @Override
    public List<Avaliacoes> buscarPorAvaliador(Avaliador avaliador) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Avaliacoes> query = em.createQuery(
                    "SELECT a FROM Avaliacoes a WHERE a.avaliadorObra = :avaliador", Avaliacoes.class);
            query.setParameter("avaliador", avaliador);
            return query.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Avaliacoes buscarPorId(Avaliacoes avaliacao) {
         em = JPAUtil.getEntityManager();
        try {
            return em.find(Avaliacoes.class, avaliacao.getId());
        } finally {
            em.close();
        }
    }

    public List<Avaliacoes> buscarAposDataComStatus(LocalDateTime dataLimite) {
        em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Avaliacoes> query = em.createQuery(
                    "SELECT a FROM Avaliacoes a WHERE a.dataAvaliacao > :data AND a.status <> :status", Avaliacoes.class);
            query.setParameter("data", dataLimite);
            query.setParameter("status", Status.PADRAO); // Enum
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

    // Busca avaliações por data e hora exatas
    @Override
    public List<Avaliacoes> buscarPorData(LocalDateTime data) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Avaliacoes> query = em.createQuery(
                    "SELECT a FROM Avaliacoes a WHERE a.dataAvaliacao = :data", Avaliacoes.class);
            query.setParameter("data", data);
            return query.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}