package main.Model.Dao;

import main.Entities.Avaliador;
import main.Entities.Obra;

import java.util.List;

public class AvaliadorDAO extends AbstractDAO implements UserGenericInterDAO<Avaliador>  {


    @Override
    public void removerPorCPF(String cpf) {

    }

    @Override
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
}
