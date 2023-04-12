package us.obviously.itmo.prog.server;


import us.obviously.itmo.prog.common.data.DataCollection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Server implements ServerConnectionManager {
    private static InetAddress host;
    private static ServerSocketChannel server;
    private static SocketChannel client;
    private static SocketAddress address;
    private ByteBuffer byteBuffer;
    private boolean isActive;
    private DataCollection data;
    public Server(DataCollection dataCollection){
        this.data = dataCollection;
    }
    //TODO Exceptions
    @Override
    public void run(int port) throws IOException {
        server = ServerSocketChannel.open();
        activeServer();
        address = new InetSocketAddress(port);
        server.bind(address);
        client = server.accept();
        System.out.print("Клиент подключился!! - ");
        System.out.println(client.getRemoteAddress());
        while (isActive){
            waitRequest();
        }
        server.close();
    }

    @Override
    public ByteBuffer read() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = client.read(buffer);
        buffer.flip();
        if (read == -1){
            //TODO replace this Exception to new one
            throw new IOException("SocketClosed");
        }
        return buffer;
    }


    @Override
    public void write(ByteBuffer data) {

    }

    @Override
    public void waitRequest() throws IOException {
        byteBuffer = read();
        String result = UTF_8.decode(byteBuffer).toString();
        System.out.println(result);
        byteBuffer.clear();
        if (result.equals("exit")){
            deactivateServer();
        }
    }
    void activeServer(){
        this.isActive = true;
        System.out.println("Сервер открыт!");
    }
    void deactivateServer(){
        this.isActive = false;
        System.out.println("Сервер закрыт!");
    }
}
