package main.Exceptions;

public class TipoUsuarionaoConhecido extends RuntimeException {
    public TipoUsuarionaoConhecido(String message) {
        super(message);
    }
}
