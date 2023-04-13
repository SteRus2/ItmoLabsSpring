package us.obviously.itmo.prog.client.exceptions;

public class FailedToSentRequestsException extends Exception {
    public FailedToSentRequestsException(String errorMessage) {
        super(errorMessage);
    }
}
