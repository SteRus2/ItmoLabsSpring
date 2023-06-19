package us.obviously.itmo.prog.common.server.exceptions;

public class UserDoesNotExistException  extends Exception {
    public UserDoesNotExistException(String msg) {
        super(msg);
    }
}
