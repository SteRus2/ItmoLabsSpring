package us.obviously.itmo.prog.common;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface ConnectionManager {
    void run();

    void write(ByteBuffer data) throws IOException;

    ByteBuffer read() throws IOException;
}
