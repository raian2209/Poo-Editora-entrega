package main.Model.Service;

import main.Entities.Avaliador;
import main.Model.Dao.AvaliadorDAO;

public class AvaliadorService {

    private AvaliadorDAO AvaliadorDAO = new AvaliadorDAO();

    public void  criarAvaliador(Avaliador avaliador){
        if(AvaliadorDAO.buscarPorCPF(avaliador.getCpf())==null){
            AvaliadorDAO.salvar(avaliador);
        }else {
            System.out.println("Já existe um avaliador com esse cpf");
        }
    }

    public void  deletarAvaliador(Avaliador avaliador){
        if(AvaliadorDAO.buscarPorCPF(avaliador.getCpf())==null){
            System.out.println("não existe um avaliador com esse cpf");
        }else {
            AvaliadorDAO.deletar(avaliador);
        }
    }

    public void  atualisarAvaliador(Avaliador avaliador){
        if(AvaliadorDAO.buscarPorCPF(avaliador.getCpf())==null){
            System.out.println("não existe um avaliador com esse cpf");
        }else {
            AvaliadorDAO.salvar(avaliador);
        }
    }

}
