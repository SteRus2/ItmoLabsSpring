package us.obviously.itmo.prog.server.exceptions;

public class FailedToAcceptClientException extends Exception{
    public FailedToAcceptClientException(String msg){
        super(msg);
    }
}
