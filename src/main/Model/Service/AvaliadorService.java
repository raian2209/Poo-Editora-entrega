package main.Model.Service;

import main.Entities.Avaliador;
import main.Entities.Obra;
import main.Model.Dao.AvaliadorDAO;

import java.util.List;

public class AvaliadorService implements UserInterService<Avaliador>{

    private AvaliadorDAO AvaliadorDAO = new AvaliadorDAO();

    public void  salvar(Avaliador avaliador){
        if(AvaliadorDAO.buscarPorCPF(avaliador.getCpf())==null){
            AvaliadorDAO.salvar(avaliador);
        }else {
            System.out.println("Já existe um avaliador com esse cpf");
        }
    }

    public void  deletar(Avaliador avaliador){
        if(AvaliadorDAO.buscarPorCPF(avaliador.getCpf())==null){
            System.out.println("não existe um avaliador com esse cpf");
        }else {


            AvaliadorDAO.deletar(avaliador);
        }
    }

    public void  atualisar(Avaliador avaliador){
        if(AvaliadorDAO.buscarPorCPF(avaliador.getCpf())==null){
            System.out.println("não existe um avaliador com esse cpf");
        }else {
            AvaliadorDAO.atualizar(avaliador);
        }
    }

    public List<Avaliador> BuscarTodos(){
        return AvaliadorDAO.BuscarTodos();
    }

    @Override
    public Avaliador buscarPorCPF(String cpf) {
        return AvaliadorDAO.buscarPorCPF(cpf);
    }

    @Override
    public List<Avaliador> buscarPorNome(String nome) {
        return AvaliadorDAO.buscarPorNome(nome);
    }
}
