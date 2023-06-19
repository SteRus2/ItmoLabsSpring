package us.obviously.itmo.prog.common.server.exceptions;

public class JwtTokenExpiredException extends Exception {
    public JwtTokenExpiredException(String msg) {
        super(msg);
    }
}
