package main.Entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@DiscriminatorValue("ESCRITOR")
public class Escritor extends Conta {

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Obra> obras;

    public List<Obra> getObras() {
        return obras;
    }

    public void setObras(List<Obra> obras) {
        this.obras = obras;
    }

    // construtores
    public Escritor(String nome, String cpf, String endereco) {
        super(nome, cpf, endereco);
    }

    public Escritor() {
        super();
    }


    @Override
    public String toString() {
        return super.toString() +"Escritor{" +
                "obras=" + obras +
                '}';
    }
}
