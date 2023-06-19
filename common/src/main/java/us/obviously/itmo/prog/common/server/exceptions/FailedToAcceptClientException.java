package us.obviously.itmo.prog.common.server.exceptions;

public class FailedToAcceptClientException extends Exception {
    public FailedToAcceptClientException(String msg) {
        super(msg);
    }
}
