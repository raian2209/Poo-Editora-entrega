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
    
    public Dono() {
    	super();
    };
    

}
