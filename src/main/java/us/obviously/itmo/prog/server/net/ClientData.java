package us.obviously.itmo.prog.server.net;

import java.nio.ByteBuffer;

public class ClientData {
    ByteBuffer buffer;
    public ClientData() {
        this.buffer = ByteBuffer.allocate(512);
    }
}
