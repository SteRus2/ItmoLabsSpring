package us.obviously.itmo.prog.server.exceptions;

public class FailedToCloseConnection extends Exception {
    public FailedToCloseConnection(String msg) {
        super(msg);
    }
}
