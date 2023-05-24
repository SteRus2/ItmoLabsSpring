package us.obviously.itmo.prog.server.exceptions;

public class FailedToStartServerException extends Exception {
    public FailedToStartServerException(String msg) {
        super(msg);
    }
}
