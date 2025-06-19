package main.Model.Dao;

import main.Entities.Escritor;
import main.Entities.Obra;

import java.util.List;

public class EscritorDAO extends AbstractDAO implements UserGenericInterDAO<Escritor>{


    @Override
    public void removerPorCPF(String cpf) {
    }

    @Override
    public List<Escritor> buscarPorObra(Obra obra) {
        return List.of();
    }

    @Override
    public List<Escritor> buscarPorNome(String nome) {
        return List.of();
    }

    @Override
    public void salvar(Escritor entidade) {

    }

    @Override
    public void atualizar(Escritor entidade) {

    }

    @Override
    public List<Escritor> BuscarTodos() {
        return List.of();
    }
}
