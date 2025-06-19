package main.Model.Dao;

import main.Entities.Avaliador;
import main.Entities.Obra;

import java.util.List;

public class AvaliadorDAO extends AbstractDAO implements UserGenericInterDAO<Avaliador>  {


    @Override
    public Avaliador buscarPorCPF(String cpf) {
        return null;
    }


    public List<Avaliador> buscarPorObra(Obra obra) {
        return List.of();
    }

    @Override
    public List<Avaliador> buscarPorNome(String nome) {
        return List.of();
    }

    @Override
    public void salvar(Avaliador entidade) {

    }

    @Override
    public void atualizar(Avaliador entidade) {

    }

    @Override
    public List<Avaliador> BuscarTodos() {
        return List.of();
    }

    @Override
    public void deletar(Avaliador entidade) {

    }
}
