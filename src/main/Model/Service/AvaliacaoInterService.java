package main.Model.Service;

import main.Entities.Avaliacoes;
import main.Entities.Avaliador;

import java.util.List;

public interface AvaliacaoInterService<T> extends GenericInterService<T>{
    public abstract  T buscarPorId(T avaliacao);
    public List<T> relatorioPorData(long mes, long dias);
    public List<T> relatorioPorAvaliador(Avaliador avaliador);


}
