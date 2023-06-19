package us.obviously.itmo.prog.common.server.exceptions;

public class FailedToReadRemoteException extends Exception {
    public FailedToReadRemoteException(String message) {
        super(message);
    }
}
