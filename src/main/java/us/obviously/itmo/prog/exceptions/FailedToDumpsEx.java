package us.obviously.itmo.prog.exceptions;

import java.io.IOException;

public class FailedToDumpsEx extends IOException {
    public FailedToDumpsEx(String err){
        super(err);
    }
}
