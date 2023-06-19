package us.obviously.itmo.prog.server.exceptions;

public class ActionDoesNotExistException extends Exception {
    public ActionDoesNotExistException(String msg) {
        super(msg);
    }
}
