package us.obviously.itmo.prog.common.server.exceptions;

public class FailedToCloseConnection extends Exception {
    public FailedToCloseConnection(String msg) {
        super(msg);
    }
}
