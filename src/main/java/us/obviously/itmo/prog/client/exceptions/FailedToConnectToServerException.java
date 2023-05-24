package us.obviously.itmo.prog.client.exceptions;

public class FailedToConnectToServerException extends Exception {
    public FailedToConnectToServerException(String msg) {
        super(msg);
    }
}
