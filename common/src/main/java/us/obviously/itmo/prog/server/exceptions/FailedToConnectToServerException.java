package us.obviously.itmo.prog.server.exceptions;

public class FailedToConnectToServerException extends Exception {
    public FailedToConnectToServerException(String msg) {
        super(msg);
    }
}
