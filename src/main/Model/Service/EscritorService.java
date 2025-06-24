package main.Model.Service;

import main.Entities.Escritor;
import main.Entities.Obra;
import main.Model.Dao.EscritorDAO;

import java.util.List;

public class EscritorService implements UserInterService<Escritor>{

    private EscritorDAO escritorDAO = new EscritorDAO();

    public void salvar(Escritor escritor) {
        if (escritorDAO.buscarPorCPF(escritor.getCpf()) == null) {
            escritorDAO.salvar(escritor);
        } else {
            System.out.println("Já existe um escritor com esse cpf");
        }
    }

    public void deletar(Escritor escritor) {
        if (escritorDAO.buscarPorCPF(escritor.getCpf()) == null) {
            System.out.println("Não existe um escritor com esse cpf");
        } else {
            escritorDAO.deletar(escritor);
        }
    }

    public void atualisar(Escritor escritor) {
        if (escritorDAO.buscarPorCPF(escritor.getCpf()) == null) {
            System.out.println("Não existe um escritor com esse cpf");
        } else {
            escritorDAO.atualizar(escritor);
        }
    }

    public List<Escritor> BuscarTodos(){
        return escritorDAO.BuscarTodos();
    }

    @Override
    public Escritor buscarPorCPF(String cpf) {
        return escritorDAO.buscarPorCPF(cpf);
    }

    @Override
    public List<Escritor> buscarPorNome(String nome) {
        return escritorDAO.buscarPorNome(nome);
    }
}
