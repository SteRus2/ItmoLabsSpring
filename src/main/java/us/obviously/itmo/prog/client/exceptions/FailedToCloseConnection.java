package us.obviously.itmo.prog.client.exceptions;

public class FailedToCloseConnection extends Exception {
    public FailedToCloseConnection(String msg) {
        super(msg);
    }
}
