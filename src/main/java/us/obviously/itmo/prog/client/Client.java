package us.obviously.itmo.prog.client;


import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

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
        while (isActive){
            request();
            waitResponse();
        }
        connection.close();
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
    public ByteBuffer read() {
        return null;
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
    public void waitResponse() {

    }

    @Override
    public void request() {
        Scanner scanner = new Scanner(System.in);
        myRequest = scanner.nextLine();
        buffer = ByteBuffer.wrap(myRequest.getBytes(StandardCharsets.UTF_8));
        write(buffer);
        if (myRequest.equals("exit")){
            deactivateClient();
        }
    }
}
