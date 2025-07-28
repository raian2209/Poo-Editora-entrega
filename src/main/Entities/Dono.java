package main.Entities;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;
import java.util.ArrayList;

@Entity
@DiscriminatorValue("DONO")
public class Dono extends Conta{
	
	//construtor

    public Dono(String nome, String cpf, String endereco) {
        super(nome, cpf, endereco);
    }
    public Dono(String nome, String cpf, String endereco, String senha) {
        super(nome, cpf, endereco, senha);
    }
    public Dono() {
    	super();
    };
    public Dono(long id, String nome, String cpf, String endereco,String senha) {
        super(id, nome, cpf, endereco,senha);
    }

}
