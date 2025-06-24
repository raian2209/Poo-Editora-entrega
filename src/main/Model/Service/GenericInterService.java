package main.Model.Service;

import java.util.List;

public interface GenericInterService <T>{

    public abstract void salvar(T entidade);
    public abstract void atualisar(T entidade);
    public abstract List<T> BuscarTodos();
    public abstract  void deletar(T entidade);


}
