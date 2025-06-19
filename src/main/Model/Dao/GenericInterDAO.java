package main.Model.Dao;

import main.Entities.Obra;

import java.util.List;

public interface GenericInterDAO<T> {

    public abstract void salvar(T entidade);
    public abstract void atualizar(T entidade);
    public abstract  List<T> BuscarTodos();



}
