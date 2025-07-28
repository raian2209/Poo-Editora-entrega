package main.Model.Factory;

import main.Entities.Avaliador;
import main.Entities.Conta;
import main.Entities.Dono;
import main.Entities.Escritor;
import main.Exceptions.TipoUsuarionaoConhecido;
import main.Exceptions.UsuarioOuSenhaIncorretosException;

public class ContaFactory {

    public static Conta createConta(Conta conta) throws IllegalArgumentException , UsuarioOuSenhaIncorretosException{

        if (conta != null) {

            if ("AVALIADOR".equalsIgnoreCase(conta.getTipo())) {
                return new Avaliador(conta.getId(),conta.getNome(), conta.getCpf(), conta.getEndereco(), conta.getSenha());
            } else if ("ESCRITOR".equalsIgnoreCase(conta.getTipo())) {
                return new Escritor(conta.getId(), conta.getNome(), conta.getCpf(), conta.getEndereco(), conta.getSenha());
            } else if ("DONO".equalsIgnoreCase(conta.getTipo())) {
                return new Dono(conta.getId(),conta.getNome(), conta.getCpf(), conta.getEndereco(), conta.getSenha());
            } else {
                throw new TipoUsuarionaoConhecido("Tipo de conta desconhecido: " + conta.getTipo());
            }
        } else {
            throw new UsuarioOuSenhaIncorretosException("Não existe esta Conta no Banco" + conta.getCpf());
        }
    }
}
