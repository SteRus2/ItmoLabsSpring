package us.obviously.itmo.prog.common;

import java.io.IOException;

public interface ConnectionManager {
    void run(int port) throws IOException;
    void write(Byte[] data);
    Byte[] read();
}
