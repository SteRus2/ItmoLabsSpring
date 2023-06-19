package us.obviously.itmo.prog.common.server.exceptions;

public class FailedToCloseServerException extends Exception {
    public FailedToCloseServerException(String msg) {
        super(msg);
    }
}
