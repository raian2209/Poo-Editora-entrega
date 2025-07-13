package main.Exceptions;

public class CPFInvalidoException extends IllegalArgumentException {
    public CPFInvalidoException(String message) {
        super(message);
    }
}
