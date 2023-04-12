package us.obviously.itmo.prog.common;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface ConnectionManager {
    void run(int port) throws IOException;
    void write(ByteBuffer data);
    ByteBuffer read() throws IOException;
}
