package main.Entities;
import java.util.List;
import java.util.ArrayList;


public class Dono extends Conta{
	
	//construtor

    public Dono(String nome, String cpf, String endereco) {
        super(nome, cpf, endereco);
    }
    
    public Dono() {
    	super();
    };
    
  //métodos da classe
    
    public Dono cadastrarDono(Dono dono) {
    	return dono;
    }
    
    public Dono alterarDono(Dono dono) {
    	return dono;
    }
    
    public Dono excluirDono(Dono dono) {
    	return dono;
    }
    
    public List<Dono> buscarTodosDono() {
    	return new ArrayList<>();
    }
    
    public List<Dono> buscarPorNome(String nome) {
    	return new ArrayList<>();
    }

    public List<Dono> buscarPorObra(String obra){
    	return new ArrayList<>();
    }
}
