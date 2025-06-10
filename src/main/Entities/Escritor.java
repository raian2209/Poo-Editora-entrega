package main.Entities;

import java.util.UUID;

public class Escritor {
    private long id;
    private String nome;
    private String cpf;
    private String endereco;

    public Escritor(String nome, String cpf, String endereco) {
        setNome(nome);
        setCpf(cpf);
        setEndereco(endereco);
    }

    public Escritor() {
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



    // ESTA VOID MAS O RETORNO É ResultSet
    public void buscarTodosEscritor(){

        System.out.println("Tentando conexão do banco");

        System.out.println("Montando query ");

        System.out.println("preparando statment ");

        System.out.println("Inserindo valores na query");

        System.out.println("EXECUTANDO");



    }

    // Retorno é ResultSet(BANCO) ou List<Escritor>
    public void buscarPorNome(String string){

        System.out.println("Tentando conexão do banco");

        System.out.println("Montando query ");

        System.out.println("preparando statment ");

        System.out.println("Inserindo valores na query");

        System.out.println("EXECUTANDO");



    }


    // Retorno é ResultSet(BANCO) ou List<Escritor>
    public void buscarPorObra(String string){

        System.out.println("Tentando conexão do banco");

        System.out.println("Montando query ");

        System.out.println("preparando statment ");

        System.out.println("Inserindo valores na query");

        System.out.println("EXECUTANDO");

    }


}
