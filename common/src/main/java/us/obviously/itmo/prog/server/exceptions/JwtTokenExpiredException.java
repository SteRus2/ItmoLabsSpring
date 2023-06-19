package us.obviously.itmo.prog.server.exceptions;

public class JwtTokenExpiredException extends Exception {
    public JwtTokenExpiredException(String msg) {
        super(msg);
    }
}
