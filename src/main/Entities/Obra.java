package main.Entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Obra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String titulo;
    @Column
    private String genero;
    @Column
    private int ano;

    @ManyToOne
    @JoinColumn(name="autor_id")
    private Escritor autor;

    // O GENERO SERIA MELHOR COMO ENUM ?
    
    // construtor
    public Obra(String titulo, String genero, int ano, Escritor autor) {
        setTitulo(titulo);
        setGenero(genero);
        setAno(ano);
        setAutor(autor);
    }

    public Obra() {
    }

    // sets e gets
    public void setId(long id) {
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

    public void setAutor(Escritor autor) {
         if(!(autor == null)) this.autor = autor;
    }

    public long getId() {
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

    public Escritor getAutor() {
        return autor;
    }

    @Override
    public String toString() {
        return "Obra{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", ano=" + ano +
                ", autor=" + autor.getNome() +
                '}';
    }
}
