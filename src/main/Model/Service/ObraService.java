package main.Model.Service;

import main.Entities.Escritor;
import main.Entities.Obra;
import main.Model.Dao.EscritorDAO;
import main.Model.Dao.ObraDAO;

import java.util.ArrayList;
import java.util.List;

public class ObraService implements ObraInterService<Obra>{
    private EscritorDAO escritorDAO = new EscritorDAO();
    private ObraDAO obraDAO= new ObraDAO();


    public Obra buscarPorId(Obra obra){
            return obraDAO.buscarPorId(obra);
    }

    public void salvar(Obra obra){
        if(escritorDAO.buscarPorCPF(obra.getAutor().getCpf())==null){
            System.out.println("Não existe esse autor ");
        }else {
            obraDAO.salvar(obra);
        }
    }

    public void deletar(Obra obra){
        if(obraDAO.buscarPorTitulo(obra.getTitulo())==null){
            System.out.println("Não existe essa obra ");
        }else {
            obraDAO.deletar(obra);
        }
    }

    public void atualisar(Obra obra){
        if(obraDAO.buscarPorId(obra)==null){
            System.out.println("Não existe essa obra ");
        }else {
            obraDAO.atualizar(obra);
        }
    }

    public List<Obra> BuscarTodos(){
        return obraDAO.BuscarTodos();
    }

    @Override
    public void deletarPorTitulo(String titulo) {
        if(obraDAO.buscarPorTitulo(titulo)==null){
            System.out.println("Não existe essa obra ");
        }else {
            obraDAO.deletar(obraDAO.buscarPorTitulo(titulo).get(0));
        }
    }

    @Override
    public List<Obra> buscarPorEscritor(Escritor escritor) {
        List<Obra> obrasEncontradas = obraDAO.buscarPorEscritor(escritor);
        if(obrasEncontradas.isEmpty()){
            System.out.println("Nenhuma obra encontrada para o escritor: " + escritor.getNome());
            return new ArrayList<>();
        }
        return obrasEncontradas;

    }



    @Override
    public List<Obra> buscarPorTitulo(String titulo) {
        return obraDAO.buscarPorTitulo(titulo);
    }

    @Override
    public List<Obra> buscarPorAno(int ano) {
        return obraDAO.buscarPorAno(ano);
    }
}
