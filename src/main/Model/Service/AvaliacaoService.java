package main.Model.Service;

import main.Entities.Avaliacoes;
import main.Entities.Avaliador;
import main.Entities.Obra;
import main.Model.Dao.AvaliacaoDAO;
import main.Model.Dao.AvaliadorDAO;
import main.Model.Dao.ObraDAO;

import java.time.LocalDateTime;
import java.util.List;

public class AvaliacaoService implements AvaliacaoInterService<Avaliacoes>{
    private AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
    private ObraDAO obraDAO = new ObraDAO();
    private AvaliadorDAO avaliadorDAO= new AvaliadorDAO();

    public void  salvar(Avaliacoes avaliacao){
    if((obraDAO.buscarPorTitulo(avaliacao.getObraAvaliar().getTitulo())==null) || (avaliadorDAO.buscarPorCPF(avaliacao.getAvaliadorObra().getCpf())==null)) {
            System.out.println("Não existe esse avaliador ou essa obra");
    } else {
        avaliacaoDAO.salvar(avaliacao);
    }
    }

    public void deletar(Avaliacoes avaliacao){
        if(avaliacaoDAO.buscarPorId(avaliacao.getId())==null) {
            System.out.println("Não existe essa avaliação");
        } else {
            avaliacaoDAO.deletar(avaliacao);
        }
    }

    public void  atualisar(Avaliacoes avaliacao){
        if(avaliacaoDAO.buscarPorId(avaliacao.getId())==null) {
            System.out.println("Não existe esse avaliação");
        } else {
            avaliacaoDAO.atualizar(avaliacao);
        }
    }

    public List<Avaliacoes> avaliadoAteData(LocalDateTime tempo){
        return avaliacaoDAO.buscarAposDataComStatusAvaliado(tempo);
    }

    @Override
    public Avaliacoes buscarPorId(Avaliacoes avaliacao) {
        return null;
    }

    public List<Avaliacoes> relatorioPorData(long mes, long dias){
       long calc = 30*mes+dias;
       LocalDateTime data = LocalDateTime.now().minusDays(calc);
        return avaliadoAteData(data);
    }

    public List<Avaliacoes> relatorioPorAvaliador(Avaliador avaliador){
        return avaliacaoDAO.buscarPorAvaliadorComStatusAvaliado(avaliador);
    }

    public List<Avaliacoes> avaliacoesNaoAvaliadas(Avaliador  avaliador){
        return avaliacaoDAO.buscarPorAvaliadorComStatusNaoAvaliado(avaliador);
    }

    public List<Avaliacoes> avaliacoesAvaliadas(){return avaliacaoDAO.buscarStatusAvaliado();}

    public List<Avaliacoes> BuscarTodos(){
        return avaliacaoDAO.BuscarTodos();
    }


}
