package main.Model.Dao;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import main.Entities.Conta;
import main.Util.JPAUtil;

import java.util.List;

public class ContaDAO extends AbstractDAO implements UserGenericInterDAO<Conta> {

    @Override
    public Conta buscarPorCPF(String cpf) {
        em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Conta> query = em.createQuery(
                    "SELECT c FROM Conta c WHERE c.cpf = :cpf", Conta.class);
            query.setParameter("cpf", cpf);
            return query.getSingleResult(); // Retorna um único resultado ou NoResultException
        } catch (NoResultException e) {
            return null; // Retorna null se não encontrar nenhuma conta com o CPF
        } finally {
            em.close();
        }
    }

    @Override
    public void salvar(Conta conta) {
        em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(conta);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao salvar Conta: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public void atualizar(Conta conta) {
        em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(conta);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao atualizar Conta: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Conta> BuscarTodos() {
        em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Conta> query = em.createQuery(
                    "SELECT c FROM Conta c", Conta.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void deletar(Conta entidade) {
        em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Conta gerenciada = em.find(Conta.class, entidade.getId()); // Encontra a entidade no contexto de persistência
            if (gerenciada != null) {
                em.remove(gerenciada);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao deletar Conta: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Conta> buscarPorNome(String nome) {
        em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Conta> query = em.createQuery(
                    "SELECT c FROM Conta c WHERE c.nome LIKE :nome", Conta.class);
            query.setParameter("nome", "%" + nome + "%"); // Usa LIKE para busca parcial
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Conta buscarPorSenha(String senha) {
        em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Conta> query = em.createQuery(
                    "SELECT c FROM Conta c WHERE c.senha = :senha", Conta.class);
            query.setParameter("senha", senha);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao buscar Conta por senha: " + e.getMessage());
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}