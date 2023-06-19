package us.obviously.itmo.prog.server.exceptions;

public class UserDoesNotExistException  extends Exception {
    public UserDoesNotExistException(String msg) {
        super(msg);
    }
}
