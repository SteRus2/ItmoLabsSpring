package us.obviously.itmo.prog.exceptions;

public class RecurrentExecuteScripts extends Exception {
    public RecurrentExecuteScripts(String errorMessage) {
        super(errorMessage);
    }
}
