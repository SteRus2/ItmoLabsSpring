package us.obviously.itmo.prog.common.server.exceptions;

public class ActionDoesNotExistException extends Exception {
    public ActionDoesNotExistException(String msg) {
        super(msg);
    }
}
