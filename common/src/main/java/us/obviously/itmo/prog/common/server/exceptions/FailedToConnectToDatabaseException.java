package us.obviously.itmo.prog.common.server.exceptions;

public class FailedToConnectToDatabaseException extends Exception {
    public FailedToConnectToDatabaseException(String msg) {
        super(msg);
    }
}
