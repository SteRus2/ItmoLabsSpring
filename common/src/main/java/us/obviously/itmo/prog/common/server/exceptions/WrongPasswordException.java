package us.obviously.itmo.prog.common.server.exceptions;

public class WrongPasswordException extends Exception {
    public WrongPasswordException(String msg) {
        super(msg);
    }
}
