package main.Entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@DiscriminatorValue("AVALIADOR")
public class Avaliador extends Conta {

    @OneToMany(mappedBy = "avaliadorObra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Avaliacoes> avaliacao;

    public List<Avaliacoes> getAvaliar() {
        return avaliacao;
    }

    public void setAvaliar(List<Avaliacoes> avaliar) {
        this.avaliacao = avaliar;
    }

    // construtor
    public Avaliador(String nome, String cpf, String endereco) {
        super(nome, cpf, endereco);
    }

    public Avaliador() {
        super();
    }

    @Override
    public String toString() {
        return super.toString()+"Avaliador{" +
                "avaliacao=" + avaliacao +
                '}';
    }
}
