package us.obviously.itmo.prog.common.server.exceptions;

public class FailedToRegisterUserException extends Exception {
    public FailedToRegisterUserException(String msg) {
        super(msg);
    }
}
