package main.Model.Dao;

import main.Entities.Escritor;
import main.Entities.Obra;

import java.util.List;

public class ObraDAO extends AbstractDAO implements ObraGenericInterDAO<Obra>{
    @Override
    public void removerPorTitulo(String titulo) {
    }

    @Override
    public List<Obra> buscarPorEscritor(Escritor escritor) {
        return List.of();
    }

    @Override
    public List<Obra> buscarPorTitulo(String titulo) {
        return List.of();
    }

    @Override
    public List<Obra> buscarPorAno(int ano) {
        return List.of();
    }

    @Override
    public void salvar(Obra entidade) {

    }

    @Override
    public void atualizar(Obra entidade) {

    }

    @Override
    public List<Obra> BuscarTodos() {
        return List.of();
    }
}
