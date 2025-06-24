package main.Model.Service;

import java.util.List;

public interface UserInterService<T> extends GenericInterService<T>{
    public abstract T buscarPorCPF(String cpf);
    //    public abstract List<T> buscarPorObra(Obra obra);
    public abstract List<T> buscarPorNome(String nome);

}
