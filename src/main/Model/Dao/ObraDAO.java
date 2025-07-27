package main.Model.Dao;

import jakarta.persistence.TypedQuery;
import main.Entities.Avaliacoes;
import main.Entities.Escritor;
import main.Entities.Obra;
import main.Util.JPAUtil;
import java.util.List;

public class ObraDAO extends AbstractDAO implements ObraGenericInterDAO<Obra> {

    @Override
    public void salvar(Obra entidade) {
        em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entidade);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao salvar Obra: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public void atualizar(Obra entidade) {
        em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entidade);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao atualizar Obra: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Obra> BuscarTodos() {
        em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Obra> query = em.createQuery("SELECT o FROM Obra o", Obra.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void deletar(Obra entidade) {
        em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Obra gerenciado = em.find(Obra.class, entidade.getId());

                em.remove(gerenciado);

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao deletar Obra: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public void removerPorTitulo(String titulo) {
        em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Obra o WHERE o.titulo = :titulo")
                    .setParameter("titulo", titulo)
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao remover obra por título: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Obra> buscarPorEscritor(Escritor escritor) {
        em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Obra> query = em.createQuery("SELECT o FROM Obra o WHERE o.autor = :autor", Obra.class);
            query.setParameter("autor", escritor);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Obra> buscarPorTitulo(String titulo) {
        em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Obra> query = em.createQuery("SELECT o FROM Obra o WHERE o.titulo LIKE :titulo", Obra.class);
            query.setParameter("titulo", "%" + titulo + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Obra> buscarPorAno(int ano) {
        em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Obra> query = em.createQuery("SELECT o FROM Obra o WHERE o.ano = :ano", Obra.class);
            query.setParameter("ano", ano);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Obra buscarPorId(Obra obra) {
        em = JPAUtil.getEntityManager();
        try {
            return em.find(Obra.class, obra.getId());
        } finally {
            em.close();
        }
    }
}
