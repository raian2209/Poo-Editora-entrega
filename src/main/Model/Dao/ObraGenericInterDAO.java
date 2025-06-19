package main.Model.Dao;

import main.Entities.Escritor;
import main.Entities.Obra;

import java.util.List;

public interface ObraGenericInterDAO<T> extends GenericInterDAO<T>{

    public abstract void removerPorTitulo(String titulo);
    public abstract List<T> buscarPorEscritor(Escritor escritor);
    public abstract List<T> buscarPorTitulo(String titulo);
    public abstract List<T> buscarPorAno(int ano);



}
