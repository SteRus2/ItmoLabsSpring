package us.obviously.itmo.prog.common.server.exceptions;

public class FailedToSentRequestsException extends Exception {
    public FailedToSentRequestsException(String errorMessage) {
        super(errorMessage);
    }
}
