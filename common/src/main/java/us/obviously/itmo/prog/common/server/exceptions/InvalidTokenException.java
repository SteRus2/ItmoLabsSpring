package us.obviously.itmo.prog.common.server.exceptions;

public class InvalidTokenException extends Exception {
    public InvalidTokenException(String msg) {
        super(msg);
    }
}
