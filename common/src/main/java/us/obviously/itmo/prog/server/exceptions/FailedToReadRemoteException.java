package us.obviously.itmo.prog.server.exceptions;

public class FailedToReadRemoteException extends Exception {
    public FailedToReadRemoteException(String message) {
        super(message);
    }
}
