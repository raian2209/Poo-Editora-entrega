package main.Model.Dao;

import jakarta.persistence.TypedQuery;
import main.Entities.Escritor;
import main.Entities.Obra;
import main.Util.JPAUtil;

import java.util.List;

public class ObraDAO extends AbstractDAO implements ObraGenericInterDAO<Obra> {

    @Override
    public void removerPorTitulo(String titulo) {
        try {
            em.getTransaction().begin();

            TypedQuery<Obra> query = em.createQuery(
                    "SELECT o FROM Obra o WHERE o.titulo = :titulo", Obra.class);
            query.setParameter("titulo", titulo);
            List<Obra> obras = query.getResultList();

            for (Obra obra : obras) {
                em.remove(obra);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            System.err.println("Erro ao remover obra por título: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Obra> buscarPorEscritor(Escritor escritor) {
        TypedQuery<Obra> query = em.createQuery(
                "SELECT o FROM Obra o WHERE o.autor = :autor", Obra.class);
        query.setParameter("autor", escritor);
        return query.getResultList();
    }

    @Override
    public List<Obra> buscarPorTitulo(String titulo) {
        TypedQuery<Obra> query = em.createQuery(
                "SELECT o FROM Obra o WHERE o.titulo LIKE :titulo", Obra.class);
        query.setParameter("titulo", "%" + titulo + "%");
        return query.getResultList();
    }

    @Override
    public List<Obra> buscarPorAno(int ano) {
        TypedQuery<Obra> query = em.createQuery(
                "SELECT o FROM Obra o WHERE o.ano = :ano", Obra.class);
        query.setParameter("ano", ano);
        return query.getResultList();
    }

    @Override
    public void salvar(Obra entidade) {
        try {
            em.getTransaction().begin();
            em.persist(entidade);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            System.err.println("Erro ao salvar Obra: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public void atualizar(Obra entidade) {
        try {
            em.getTransaction().begin();
            em.merge(entidade);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            System.err.println("Erro ao atualizar Obra: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Obra> BuscarTodos() {
        TypedQuery<Obra> query = em.createQuery("SELECT o FROM Obra o", Obra.class);
        return query.getResultList();
    }
    
    @Override
    public void deletar(Obra entidade) {
        try {
            em.getTransaction().begin();
            if (entidade != null) {
                if (!em.contains(entidade)) {
                    entidade = em.merge(entidade);
                }
                em.remove(entidade);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
