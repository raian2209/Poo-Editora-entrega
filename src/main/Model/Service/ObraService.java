package main.Model.Service;

import main.Entities.Obra;
import main.Model.Dao.EscritorDAO;
import main.Model.Dao.ObraDAO;

public class ObraService {
    private EscritorDAO escritorDAO = new EscritorDAO();
    private ObraDAO obraDAO= new ObraDAO();

    public void criarObra(Obra obra){
        if(escritorDAO.buscarPorCPF(obra.getAutor().getCpf())==null){
            System.out.println("Não existe esse autor ");
        }else {
            obraDAO.salvar(obra);
        }
    }

    public void deletarObra(Obra obra){
        if(obraDAO.buscarPorId(obra)==null){
            System.out.println("Não existe essa obra ");
        }else {
            obraDAO.deletar(obra);
        }
    }

    public void atualizarObra(Obra obra){
        if(obraDAO.buscarPorId(obra)==null){
            System.out.println("Não existe essa obra ");
        }else {
            obraDAO.atualizar(obra);
        }
    }

}
