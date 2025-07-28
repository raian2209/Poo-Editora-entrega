package main.Entities;

import jakarta.persistence.*;
import org.hibernate.Hibernate; // Importação
import java.util.List;

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
    public Avaliador(String nome, String cpf, String endereco, String senha) {
        super(nome, cpf, endereco, senha);
    }

    public Avaliador() {
        super();
    }
    public Avaliador(long id, String nome, String cpf, String endereco,String senha) {
        super(id, nome, cpf, endereco,senha);
    }
    //alteracao no tostring
    @Override
    public String toString() {
        boolean isInitialized = Hibernate.isInitialized(this.avaliacao);
        return super.toString() + "Avaliador{" +
                "avaliacao=" + (isInitialized ? this.avaliacao.size() + " avaliações" : "[coleção não inicializada]") +
                '}';
    }
}