package main.Entities;

import jakarta.persistence.*;
import org.hibernate.Hibernate; //Importação

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("ESCRITOR")
public class Escritor extends Conta {

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Obra> obras = new ArrayList<>();

    public List<Obra> getObras() {
        return obras;
    }

    public void setObras(List<Obra> obras) {
        this.obras = obras;
    }

    public Escritor(long id, String nome, String cpf, String endereco,String senha) {
        super(id, nome, cpf, endereco,senha);
    }

    // construtores
    public Escritor(String nome, String cpf, String endereco) {
        super(nome, cpf, endereco);
    }
    public Escritor(String nome, String cpf, String endereco, String senha) {
        super(nome, cpf, endereco, senha);
    }

    public Escritor() {
        super();
    }

    // Alteração no to String
    @Override
    public String toString() {
        boolean isInitialized = Hibernate.isInitialized(this.obras);
        return super.toString() + " Escritor{" +
                "obras=" + (isInitialized ? this.obras.size() + " obras" : "[coleção não inicializada]") +
                '}';
    }
}