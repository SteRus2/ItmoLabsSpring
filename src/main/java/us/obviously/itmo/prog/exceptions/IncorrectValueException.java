package us.obviously.itmo.prog.exceptions;

public class IncorrectValueException extends Exception {
    public IncorrectValueException(String errorMessage) {
        super(errorMessage);
    }
}
