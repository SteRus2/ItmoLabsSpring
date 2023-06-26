package us.obviously.itmo.prog.common.server.exceptions;

public class ExecuteCommandException extends Exception {
    public final int lineNumber;
    public final String filepath;

    public ExecuteCommandException(String filepath, int lineNumber, String message) {
        super(message);
        this.filepath = filepath;
        this.lineNumber = lineNumber;
    }
}
