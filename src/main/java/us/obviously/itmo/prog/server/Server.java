package us.obviously.itmo.prog.server;


import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.exceptions.FailedToReadRemoteException;
import us.obviously.itmo.prog.client.exceptions.FailedToSentRequestsException;
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
    @Override
    public void run(int port) {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            activeServer();
            address = new InetSocketAddress(port);
            server.bind(address);
            client = server.accept();
            System.out.println("Клиент подключился! - " + client.getRemoteAddress());
            while (isActive){
                waitRequest();
            }
        } catch (IOException e){
            Messages.printStatement("~reНам не удалось запустить сервер, видимо, по разным причинам: " + e.getMessage() + "~=");
            deactivateServer();
        } catch (FailedToReadRemoteException e) {
            Messages.printStatement("~reКлиент не умеет общаться, мы закрываемся: " + e.getMessage() + "~=");
            deactivateServer();
        } catch (FailedToSentRequestsException e) {
            Messages.printStatement("~reНам не удалось отправить ответ клиенту, но мы, как программа уважающая людей, сожалеем об этом: " + e.getMessage() + "~=");
            deactivateServer();
        }
        try {
            server.close();
        } catch (IOException e) {
            Messages.printStatement("~reОк, мы попытались закрыть сервер, у нас не получилось, наверное что-то пошло не так, мы надеемся, что сейчас сервер закрыт. " + e.getMessage() + "~=");
        }
    }

    @Override
    public ByteBuffer read() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = client.read(buffer);
        buffer.flip();
        if (read == -1){
            throw new IOException("Сокет закрыт");
        }
        return buffer;
    }


    @Override
    public void write(ByteBuffer data) throws IOException {
        client.write(data);
    }

    @Override
    public void waitRequest() throws FailedToReadRemoteException, FailedToSentRequestsException {
        try {
            byteBuffer = read();
        } catch (IOException e) {
            throw new FailedToReadRemoteException("Сервер не может получить данные от Клиента. Он сам виновать.");
        }
        String result = UTF_8.decode(byteBuffer).toString();
        System.out.println(result + " - from server");
        giveResponse(result + " - from server");
        byteBuffer.clear();
        if (result.equals("exit")){
            deactivateServer();
        }
    }

    @Override
    public void giveResponse(String args) throws FailedToSentRequestsException {
        byteBuffer = ByteBuffer.wrap(args.getBytes(UTF_8));
        try {
            write(byteBuffer);
        } catch (IOException e) {
            throw new FailedToSentRequestsException("У нас не получилось отдать так называемый Response");
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
