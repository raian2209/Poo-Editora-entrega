package main.Entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@DiscriminatorValue("ESCRITOR")
public class Escritor extends Conta {

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Obra> obras;

    
    // construtores
    public Escritor(String nome, String cpf, String endereco) {
        super(nome, cpf, endereco);
    }

    public Escritor() {
        super();
    }



}
