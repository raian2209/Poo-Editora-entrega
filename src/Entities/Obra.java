package Entities;

import java.util.UUID;

public class Obra {
    private UUID id;
    private String titulo;
    private String genero;
    private int ano;
    private String autor;

    // O GENERO SERIA MELHOR COMO ENUM ?
//
    public Obra(String titulo, String genero, int ano, String autor) {
        setTitulo(titulo);
        setGenero(genero);
        setAno(ano);
        setAutor(autor);
    }

    public Obra() {
    }


    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {         
        if(!(titulo.isBlank())) this.titulo = titulo; 
        else this.titulo = "DEFAULT";  
    }

    public void setGenero(String genero) {
         if(!(genero.isBlank())) this.genero = genero; 
        else this.genero = "DEFAULT";  

    }

    public void setAno(int ano) {
        if(ano<=0 ) this.ano = 1;

        this.ano = ano;
    }

    public void setAutor(String autor) {
         if(!(autor.isBlank())) this.autor = autor; 
        else this.autor = "DEFAULT";  
    }

    public UUID getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public int getAno() {
        return ano;
    }

    public String getAutor() {
        return autor;
    }

    public Obra cadastrarObra(Obra obra){

        System.out.println("Tentando conexão do banco");

        System.out.println("Montando query ");

        System.out.println("preparando statment ");

        System.out.println("Inserindo valores na query");

        System.out.println("EXECUTANDO");


        return obra;
    }

    public Obra alterarObra(Obra obra){

        System.out.println("Tentando conexão do banco");

        System.out.println("Montando query ");

        System.out.println("preparando statment ");

        System.out.println("Inserindo valores na query");

        System.out.println("EXECUTANDO");


        return obra;
    }

    public Obra exluirObra(Obra obra){

        System.out.println("Tentando conexão do banco");

        System.out.println("Montando query ");

        System.out.println("preparando statment ");

        System.out.println("Inserindo valores na query");

        System.out.println("EXECUTANDO");


        return obra;
    }



    // Retorno é ResultSet(BANCO) ou List<Obras>
    public void buscarTodasObra(){

        System.out.println("Tentando conexão do banco");

        System.out.println("Montando query ");

        System.out.println("preparando statment ");

        System.out.println("Inserindo valores na query");

        System.out.println("EXECUTANDO");



    }

    // Retorno é ResultSet(BANCO) ou List<Obras>
    public void buscarPorEscritor(String string){

        System.out.println("Tentando conexão do banco");

        System.out.println("Montando query ");

        System.out.println("preparando statment ");

        System.out.println("Inserindo valores na query");

        System.out.println("EXECUTANDO");



    }


    // Retorno é ResultSet(BANCO) ou List<Obras>
    public void buscarPorTitulo(String string){

        System.out.println("Tentando conexão do banco");

        System.out.println("Montando query ");

        System.out.println("preparando statment ");

        System.out.println("Inserindo valores na query");

        System.out.println("EXECUTANDO");



    }

    // Retorno é ResultSet(BANCO) ou List<Obras>
    public void buscarPorStatus(String string){

        System.out.println("Tentando conexão do banco");

        System.out.println("Montando query ");

        System.out.println("preparando statment ");

        System.out.println("Inserindo valores na query");

        System.out.println("EXECUTANDO");



    }

    // Retorno é ResultSet(BANCO) ou List<Obras>
    public void buscarPorAno(String string){

        System.out.println("Tentando conexão do banco");

        System.out.println("Montando query ");

        System.out.println("preparando statment ");

        System.out.println("Inserindo valores na query");

        System.out.println("EXECUTANDO");



    }


}
