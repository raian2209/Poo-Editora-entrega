package main.Entities;

import java.util.UUID;

public class Avaliador extends Conta {


    // construtor
    public Avaliador(String nome, String cpf, String endereco) {
        super(nome, cpf, endereco);
    }

    public Avaliador() {
        super();
    }

    // métodos da classe
    public Avaliador cadastrarAvaliador(Avaliador avaliador) {
    	return avaliador;
    }

    public Avaliador alterarAvaliador(Avaliador avaliador) {
    	return avaliador;
    }

    public Avaliador excluirAvaliador(Avaliador avaliador) {
    	return avaliador;
    }

    public List<Avaliador> buscarTodosAvaliador() {
    	return new ArrayList<>();
    }
    
    public List<Avaliador> buscarPorNome(String nome) {
    	return new ArrayList<>();
    }

    public List<Avaliador> buscarPorObra(String obra){
    	return new ArrayList<>();
    }

}
