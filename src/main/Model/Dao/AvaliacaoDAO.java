package main.Model.Dao;

import main.Entities.Avaliacoes;
import main.Entities.Avaliador;

import java.time.LocalDateTime;
import java.util.List;

public  class AvaliacaoDAO extends AbstractDAO implements AvaliacaoInterDAO<Avaliacoes>{
    @Override
    public List<Avaliacoes> buscarPorAvaliador(Avaliador avaliador) {
        return List.of();
    }

    @Override
    public List<Avaliacoes> buscarPorData(LocalDateTime data) {
        return List.of();
    }

    @Override
    public void salvar(Avaliacoes entidade) {

    }

    @Override
    public void atualizar(Avaliacoes entidade) {

    }

    @Override
    public List<Avaliacoes> BuscarTodos() {
        return List.of();
    }
}
