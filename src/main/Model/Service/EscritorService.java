package main.Model.Service;

import main.Entities.Escritor;
import main.Model.Dao.EscritorDAO;

public class EscritorService {

    private EscritorDAO escritorDAO = new EscritorDAO();

    public void criarEscritor(Escritor escritor) {
        if (escritorDAO.buscarPorCPF(escritor.getCpf()) == null) {
            escritorDAO.salvar(escritor);
        } else {
            System.out.println("Já existe um escritor com esse cpf");
        }
    }

    public void deletarEscritor(Escritor escritor) {
        if (escritorDAO.buscarPorCPF(escritor.getCpf()) == null) {
            System.out.println("Não existe um escritor com esse cpf");
        } else {
            escritorDAO.deletar(escritor);
        }
    }

    public void atualisarEscritor(Escritor escritor) {
        if (escritorDAO.buscarPorCPF(escritor.getCpf()) == null) {
            System.out.println("Não existe um escritor com esse cpf");
        } else {
            escritorDAO.salvar(escritor);
        }
    }
}
