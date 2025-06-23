package main.Model.Service;

import main.Entities.Avaliacoes;
import main.Model.Dao.AvaliacaoDAO;
import main.Model.Dao.AvaliadorDAO;
import main.Model.Dao.ObraDAO;

import java.time.LocalDateTime;
import java.util.List;

public class AvaliacaoService {
    private AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
    private ObraDAO obraDAO = new ObraDAO();
    private AvaliadorDAO avaliadorDAO= new AvaliadorDAO();

    public void  criarAvaliacao(Avaliacoes avaliacao){
    if((obraDAO.buscarPorTitulo(avaliacao.getObraAvaliar().getTitulo())==null) || (avaliadorDAO.buscarPorCPF(avaliacao.getAvaliadorObra().getCpf())==null)) {
            System.out.println("Não existe esse avaliador ou essa obra");
    } else {
        avaliacaoDAO.salvar(avaliacao);
    }
    }

    public void  deletarAvaliacao(Avaliacoes avaliacao){
        if(avaliacaoDAO.buscarPorId(avaliacao)==null) {
            System.out.println("Não existe essa avaliação");
        } else {
            avaliacaoDAO.deletar(avaliacao);
        }
    }

    public void  atualisarAvaliacao(Avaliacoes avaliacao){
        if(avaliacaoDAO.buscarPorId(avaliacao)==null) {
            System.out.println("Não existe esse avaliação");
        } else {
            avaliacaoDAO.deletar(avaliacao);
        }
    }

    public List<Avaliacoes> relatorio(LocalDateTime tempo){
        return avaliacaoDAO.buscarAposDataComStatus(tempo);
    }



}
