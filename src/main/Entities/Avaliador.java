package main.Entities;

import java.util.UUID;

public class Avaliador {
    private long id;
    private String nome;
    private String cpf;
    private String endereco;

    public Avaliador(String nome, String cpf, String endereco) {
        setNome(nome);
        setCpf(cpf);
        setEndereco(endereco);
    }

    public Avaliador() {
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        if(!(nome.isBlank())) this.nome = nome;
        else this.nome = "DEFAULT";
       
    }

    public void setCpf(String cpf) {
        if(!(cpf.isBlank())) this.cpf = cpf; 
        else this.cpf = "DEFAULT";   
    }

    public void setEndereco(String endereco) {
        if(!(endereco.isBlank())) this.endereco = endereco;
        else this.endereco = "DEFAULT";
        
    }

 
    public Avaliador cadastrarAvaliador(Avaliador avaliador){

        System.out.println("Tentando conexão do banco");

        System.out.println("Montando query ");

        System.out.println("preparando statment ");

        System.out.println("Inserindo valores na query");

        System.out.println("EXECUTANDO");


        return avaliador;
    }

    public Avaliador alterarAvaliador(Avaliador avaliador){

        System.out.println("Tentando conexão do banco");

        System.out.println("Montando query ");

        System.out.println("preparando statment ");

        System.out.println("Inserindo valores na query");

        System.out.println("EXECUTANDO");


        return avaliador;
    }

    public Avaliador exluirAvaliador(Avaliador avaliador){

        System.out.println("Tentando conexão do banco");

        System.out.println("Montando query ");

        System.out.println("preparando statment ");

        System.out.println("Inserindo valores na query");

        System.out.println("EXECUTANDO");


        return avaliador;
    }



    // ESTA VOID MAS O RETORNO É ResultSet
    public void buscarTodosAvaliador(){

        System.out.println("Tentando conexão do banco");

        System.out.println("Montando query ");

        System.out.println("preparando statment ");

        System.out.println("Inserindo valores na query");

        System.out.println("EXECUTANDO");



    }

    // Retorno é ResultSet(BANCO) ou List<Avaliador>
    public void buscarPorNome(String string){

        System.out.println("Tentando conexão do banco");

        System.out.println("Montando query ");

        System.out.println("preparando statment ");

        System.out.println("Inserindo valores na query");

        System.out.println("EXECUTANDO");



    }


    // Retorno é ResultSet(BANCO) ou List<Avaliador>
    public void buscarPorObra(String string){

        System.out.println("Tentando conexão do banco");

        System.out.println("Montando query ");

        System.out.println("preparando statment ");

        System.out.println("Inserindo valores na query");

        System.out.println("EXECUTANDO");

    }



}
