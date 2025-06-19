package main.Model.Dao;

import main.Entities.Obra;

import java.util.List;

public interface UserGenericInterDAO<T> extends GenericInterDAO<T> {

    public abstract T buscarPorCPF(String cpf);
//    public abstract List<T> buscarPorObra(Obra obra);
    public abstract List<T> buscarPorNome(String nome);

}
