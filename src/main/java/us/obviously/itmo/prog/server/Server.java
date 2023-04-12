package us.obviously.itmo.prog.server;

import us.obviously.itmo.prog.common.ConnectionManager;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server implements ConnectionManager {
    private static final int port = 9999;
    private static InetAddress host;
    private static ServerSocketChannel server;
    private static SocketChannel client;
    private static SocketAddress address;

    @Override
    public void run(int port) throws IOException {
        server = ServerSocketChannel.open();
        address = new InetSocketAddress(port);
        server.bind(address);
        client = server.accept();

    }

    @Override
    public Byte[] read() {
        return new Byte[0];
    }

    @Override
    public void write(Byte[] data) {

    }
}
