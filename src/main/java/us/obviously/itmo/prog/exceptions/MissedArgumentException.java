package us.obviously.itmo.prog.exceptions;

public class MissedArgumentException extends Exception {
    public MissedArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
