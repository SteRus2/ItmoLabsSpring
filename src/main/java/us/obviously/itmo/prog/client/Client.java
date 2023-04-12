package us.obviously.itmo.prog.client;

import us.obviously.itmo.prog.common.ConnectionManager;

import java.io.IOException;

public class Client implements ConnectionManager {
    @Override
    public void run(int port) throws IOException {

    }

    @Override
    public void write(Byte[] data) {

    }

    @Override
    public Byte[] read() {
        return new Byte[0];
    }
}
