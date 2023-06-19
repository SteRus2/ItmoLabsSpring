package us.obviously.itmo.prog.common.server.exceptions;

public class ServerErrorException extends Exception {
    public ServerErrorException(String msg) {
        super(msg);
    }
}
