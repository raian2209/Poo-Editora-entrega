package main.Exceptions;

public class CamposVaziosException extends IllegalArgumentException {

    public CamposVaziosException(String message) {
        super(message);
    }

}