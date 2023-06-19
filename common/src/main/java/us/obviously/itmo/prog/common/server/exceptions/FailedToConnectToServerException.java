package us.obviously.itmo.prog.common.server.exceptions;

public class FailedToConnectToServerException extends Exception {
    public FailedToConnectToServerException(String msg) {
        super(msg);
    }
}
