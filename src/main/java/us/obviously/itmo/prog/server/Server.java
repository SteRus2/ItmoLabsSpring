package us.obviously.itmo.prog.server;


import us.obviously.itmo.prog.common.data.DataCollection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Set;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Server implements ServerConnectionManager {
    private static InetAddress host;
    private static ServerSocketChannel server;
    private static SocketChannel client;
    private static SocketAddress address;
    private ByteBuffer byteBuffer;
    private boolean isActive;
    private DataCollection data;
    private Selector selector;
    public Server(DataCollection dataCollection){
        this.data = dataCollection;
    }
    //TODO Exceptions
    @Override
    public void run(int port) throws IOException {
        selector = Selector.open();
        server = ServerSocketChannel.open();
        server.configureBlocking(false);
        activateServer();
        server.register(selector, SelectionKey.OP_ACCEPT);
        while(true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            for (var iter = keys.iterator(); iter.hasNext(); ) {
                SelectionKey key = iter.next(); iter.remove();
                if (key.isValid()) {
                    if (key.isAcceptable()) { register(key); }
                    if (key.isReadable()) { doRead(key); }
                    if (key.isWritable()) { doWrite(key); }
                }
            }
        }
//        selector.close();
//        address = new InetSocketAddress(port);
//        server.bind(address);
//        client = server.accept();
//        System.out.print("Клиент подключился!! - ");
//        System.out.println(client.getRemoteAddress());
//        while (isActive){
//            waitRequest();
//        }
//        server.close();
    }

    private static void register(SelectionKey key)
            throws IOException {
        System.out.println("do register");
        ServerSocketChannel serverSocket = (ServerSocketChannel) key.channel();
        Selector selector = key.selector();
        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);
        var clientData = new ClientData();
        key.attach(clientData);
        client.register(selector, SelectionKey.OP_READ);
    }

    public void doRead(SelectionKey key) {
        System.out.println("do read");
        var sc = (SocketChannel) key.channel();
        var data = (ClientData) key.attachment();
        try {
            sc.read(data.buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            sc.register(key.selector(), SelectionKey.OP_WRITE);
        } catch (ClosedChannelException e) {
            throw new RuntimeException(e);
        }
    }

    public void doWrite(SelectionKey key) {
        System.out.println("do write");
        var sc = (SocketChannel) key.channel();
        var data = (ClientData) key.attachment();
        try {
            sc.write(data.buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        try {
            client.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void waitRequest() throws IOException {
        byteBuffer = read();
        String result = UTF_8.decode(byteBuffer).toString();
        System.out.println(result + " - from server");
        giveResponse(result + " - from server");
        byteBuffer.clear();
        if (result.equals("exit")){
            deactivateServer();
        }
    }

    @Override
    public void giveResponse(String args) {
        byteBuffer = ByteBuffer.wrap(args.getBytes(UTF_8));
        try {
            write(byteBuffer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void activateServer(){
        this.isActive = true;
        System.out.println("Сервер открыт!");
    }
    void deactivateServer(){
        this.isActive = false;
        System.out.println("Сервер закрыт!");
    }
}
