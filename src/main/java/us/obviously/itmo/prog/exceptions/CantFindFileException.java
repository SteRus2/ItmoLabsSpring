package us.obviously.itmo.prog.exceptions;

import java.io.FileNotFoundException;

public class CantFindFileException extends FileNotFoundException {
    public CantFindFileException(String s) {
        super(s);
    }
}
