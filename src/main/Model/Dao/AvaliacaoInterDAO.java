package main.Model.Dao;

import main.Entities.Avaliacoes;
import main.Entities.Avaliador;
import main.Entities.Escritor;

import java.time.LocalDateTime;
import java.util.List;

public interface AvaliacaoInterDAO<T> extends GenericInterDAO<T>{

    public abstract List<T> buscarPorAvaliador(Avaliador avaliador);
    public abstract List<T> buscarPorData(LocalDateTime data);
    public abstract  T buscarPorId(Avaliacoes avaliacao);
}
