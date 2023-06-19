package us.obviously.itmo.prog.server.exceptions;

public class ServerErrorException extends Exception {
    public ServerErrorException(String msg) {
        super(msg);
    }
}
