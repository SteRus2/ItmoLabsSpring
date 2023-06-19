package us.obviously.itmo.prog.server.exceptions;

public class FailedToSentRequestsException extends Exception {
    public FailedToSentRequestsException(String errorMessage) {
        super(errorMessage);
    }
}
