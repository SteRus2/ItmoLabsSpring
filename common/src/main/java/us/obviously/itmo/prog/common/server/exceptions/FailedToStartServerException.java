package us.obviously.itmo.prog.common.server.exceptions;

public class FailedToStartServerException extends Exception {
    public FailedToStartServerException(String msg) {
        super(msg);
    }
}
