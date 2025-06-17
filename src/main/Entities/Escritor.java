package main.Entities;

import java.util.UUID;

public class Escritor extends Conta {
    private List<Obra> obras;

    // construtores
    public Escritor(String nome, String cpf, String endereco) {
        setNome(nome);
        setCpf(cpf);
        setEndereco(endereco);
    }

    public Escritor() {
    }

    // metodos
    public Escritor cadastrarEscritor(Escritor escritor){

        System.out.println("Tentando conexão do banco");

        System.out.println("Montando query ");

        System.out.println("preparando statment ");

        System.out.println("Inserindo valores na query");

        System.out.println("EXECUTANDO");


        return escritor;
    }

    public Escritor alterarEscritor(Escritor escritor){

        System.out.println("Tentando conexão do banco");

        System.out.println("Montando query ");

        System.out.println("preparando statment ");

        System.out.println("Inserindo valores na query");

        System.out.println("EXECUTANDO");


        return escritor;
    }

    public Escritor exluirEscritor(Escritor escritor){

        System.out.println("Tentando conexão do banco");

        System.out.println("Montando query ");

        System.out.println("preparando statment ");

        System.out.println("Inserindo valores na query");

        System.out.println("EXECUTANDO");


        return escritor;
    }

    public List<Escritor> buscarTodosEscritor(){
        return new ArrayList<>();
    }

    public List<Escritor> buscarPorNome(String string){
        return new ArrayList<>();
    }

    public List<Escritor> buscarPorObra(String string){
        return new ArrayList<>();
    }


}
