package us.obviously.itmo.prog.server.exceptions;

public class WrongPasswordException extends Exception {
    public WrongPasswordException(String msg) {
        super(msg);
    }
}
