package us.obviously.itmo.prog.client;


import us.obviously.itmo.prog.common.data.DataCollection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Client implements ClientConnectionManager {
    private static InetAddress host;
    private static SocketAddress address;
    private static SocketChannel connection;
    private ByteBuffer buffer;
    private String myRequest;
    private boolean isActive;

    @Override
    public void run(int port) throws IOException {
        host = InetAddress.getLocalHost();
        address = new InetSocketAddress(host, port);
        connection = SocketChannel.open();
        connection.connect(address);
        activeClient();
    }

    @Override
    public void write(ByteBuffer data) {
        try {
            connection.write(data);
        } catch (IOException e) {
            //TODO
        }
    }

    @Override
    public ByteBuffer read() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = connection.read(buffer);
        buffer.flip();
        if (read == -1){
            //TODO replace this Exception to new one
            throw new IOException("SocketClosed");
        }
        return buffer;
    }
    void activeClient(){
        this.isActive = true;
        System.out.println("Клиент запущен!");
    }
    void deactivateClient(){
        this.isActive = false;
        System.out.println("Клиент закрыт!");
    }

    @Override
    public String waitResponse() {
        ByteBuffer byteBuffer;
        try {
            byteBuffer = read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return StandardCharsets.UTF_8.decode(byteBuffer).toString();
    }

    @Override
    public void request(String myRequest) {
        buffer = ByteBuffer.wrap(myRequest.getBytes());
        write(buffer);
    }

    @Override
    public void stop() throws IOException {
        deactivateClient();
        connection.close();
    }
}
