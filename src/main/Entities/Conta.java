
package main.Entities;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public class Conta {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String nome;
	@Column(unique = true, nullable = false)
	private String cpf;
	@Column
	private String endereco;
	
	//getters e setters
	
	public long getId() {
        return id;
    }
	
	public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        if(!(nome.isBlank())) this.nome = nome;
        else this.nome = "DEFAULT";
       
    }

    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        if(!(cpf.isBlank())) this.cpf = cpf; 
        else this.cpf = "DEFAULT";   
    }

    public String getEndereco() {
        return endereco;
    }   

    public void setEndereco(String endereco) {
        if(!(endereco.isBlank())) this.endereco = endereco;
        else this.endereco = "DEFAULT";
    }
    
    //construtores


    public Conta(long id, String nome, String cpf, String endereco) {
        setId(id);
        setNome(nome);
        setCpf(cpf);
        setEndereco(endereco);
    }

    public Conta(String nome, String cpf, String endereco) {
    	setNome(nome);
    	setCpf(cpf);
    	setEndereco(endereco);
    }
    
    public Conta() {
    	
    }

    @Override
    public String toString() {
        return "Conta{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
