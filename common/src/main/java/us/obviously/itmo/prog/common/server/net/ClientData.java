package us.obviously.itmo.prog.common.server.net;

import java.nio.ByteBuffer;

public class ClientData {
    final ByteBuffer buffer;

    public ClientData() {
        this.buffer = ByteBuffer.allocate(512);
    }
}
