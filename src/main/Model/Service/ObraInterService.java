package main.Model.Service;

import main.Entities.Escritor;

import java.util.List;

public interface ObraInterService<T> extends GenericInterService<T>{
    public abstract void deletarPorTitulo(String titulo);
    public abstract List<T> buscarPorEscritor(Escritor escritor);
    public abstract List<T> buscarPorTitulo(String titulo);
    public abstract List<T> buscarPorAno(int ano);

}
