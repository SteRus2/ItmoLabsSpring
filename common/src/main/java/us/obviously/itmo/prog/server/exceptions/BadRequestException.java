package us.obviously.itmo.prog.server.exceptions;

public class BadRequestException extends Exception {
    public BadRequestException(String msg) {
        super(msg);
    }
}
