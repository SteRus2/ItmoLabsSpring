package us.obviously.itmo.prog.exceptions;

public class UnexpectedArgumentException extends Exception {
    public UnexpectedArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
