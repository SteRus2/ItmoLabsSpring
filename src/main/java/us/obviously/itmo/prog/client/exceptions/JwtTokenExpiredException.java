package us.obviously.itmo.prog.client.exceptions;

public class JwtTokenExpiredException extends Exception {
    public JwtTokenExpiredException(String msg) {
        super(msg);
    }
}
