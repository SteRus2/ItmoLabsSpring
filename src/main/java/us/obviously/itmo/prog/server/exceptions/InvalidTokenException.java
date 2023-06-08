package us.obviously.itmo.prog.server.exceptions;

public class InvalidTokenException extends Exception {
    public InvalidTokenException(String msg) {
        super(msg);
    }
}
