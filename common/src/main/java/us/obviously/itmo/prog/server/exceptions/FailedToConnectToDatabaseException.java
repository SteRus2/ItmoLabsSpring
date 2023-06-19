package us.obviously.itmo.prog.server.exceptions;

public class FailedToConnectToDatabaseException extends Exception {
    public FailedToConnectToDatabaseException(String msg) {
        super(msg);
    }
}
