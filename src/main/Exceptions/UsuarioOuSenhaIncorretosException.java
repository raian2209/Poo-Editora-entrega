package main.Exceptions;

public class UsuarioOuSenhaIncorretosException extends RuntimeException {
    public UsuarioOuSenhaIncorretosException(String message){
        super(message);
    }

}
