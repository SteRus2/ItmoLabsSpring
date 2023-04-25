package us.obviously.itmo.prog.server.net;


import us.obviously.itmo.prog.client.console.ConsoleColor;
import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.exceptions.FailedToReadRemoteException;
import us.obviously.itmo.prog.client.exceptions.FailedToSentRequestsException;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.server.exceptions.FailedToAcceptClientException;
import us.obviously.itmo.prog.server.exceptions.FailedToCloseServerException;
import us.obviously.itmo.prog.server.exceptions.FailedToStartServerException;
import us.obviously.itmo.prog.server.serverCommands.ServerCommand;
import us.obviously.itmo.prog.server.serverCommands.ServerCommandManager;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Scanner;
import java.util.Set;

import static java.nio.channels.SelectionKey.OP_READ;
import static java.nio.charset.StandardCharsets.UTF_8;

public class Server implements ServerConnectionManager {
    private final int DATA_SIZE = 1024;
    private InetAddress host;
    private ServerSocketChannel server;
    private SocketChannel client;
    private SocketAddress address;
    private final Scanner serverCommandReader;
    private ByteBuffer byteBuffer;
    private boolean isActive;
    private final DataCollection data;
    private final Selector selector;
    private Set<SelectionKey> selectionKeySet;
    private ServerCommandManager serverCommandManager;

    {
        ConsoleColor.initColors();
        serverCommandReader = new Scanner(System.in);
    }
    public Server(DataCollection dataCollection, int port) throws FailedToStartServerException {
        this.data = dataCollection;
        byteBuffer = ByteBuffer.allocate(DATA_SIZE);
        try {
            selector = Selector.open();
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.register(selector, SelectionKey.OP_ACCEPT);
            activeServer();
            serverCommandManager = new ServerCommandManager(this);
        } catch (ClosedChannelException e){
            throw new FailedToStartServerException("Возникла ошибка при старте сервера, сетевой канал закрыт, попробуйте позже");
        } catch (IOException e){
            throw new FailedToStartServerException("Возникла ошибка при старте сервера, пожалуйста, попробуйте позднее");
        }
        try {
            address = new InetSocketAddress(port);
            server.bind(address);
        } catch (IOException e) {
            throw new FailedToStartServerException("Данный порт занят");
        }
        new Thread(){
            @Override
            public void run() {
                while (serverCommandReader.hasNextLine()) {
                    String commandString = serverCommandReader.nextLine();
                    ServerCommand command = serverCommandManager.getCommand(commandString);
                    if (command == null) {
                        System.out.println("Команды не существует");
                    } else {
                        command.execute();
                    }
                }
            }
        }.start();
    }
    @Override
    public void run() {
        while (isActive){
            try {
                int nowChannels = selector.select(1000);
                selectionKeySet = selector.selectedKeys();
                for (var iter = selectionKeySet.iterator(); iter.hasNext(); ){
                     SelectionKey key = iter.next();
                     iter.remove();
                     if (key.isValid()){
                         if(key.isAcceptable()){
                            acceptClient(key);
                         }
                         if(key.isReadable()){

                         }
                         if (key.isWritable()){

                         }
                     } else {
                         key.cancel();
                     }
                }
            } catch (IOException e) {
                Messages.printStatement("~yeunknown io exception~=");
            } catch (FailedToAcceptClientException e) {
                Messages.printStatement("~reНе получилось присоединить клиента: " + e.getMessage() + "~=");
            }
        }
    }
    private void acceptClient(SelectionKey selectionKey) throws FailedToAcceptClientException {
        var clientSocket = (ServerSocketChannel) selectionKey.channel();
        try {
            var client = clientSocket.accept();
            client.configureBlocking(false);
            client.register(selectionKey.selector(), OP_READ);
            System.out.println("Новый клиент: " + client.getRemoteAddress());
        } catch (IOException e) {
            throw new FailedToAcceptClientException("Во время соединения с клиентом произошла ошибка");
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
            System.out.println(123);
            byteBuffer.clear();
            byteBuffer = read();
            System.out.println(234);
        } catch (IOException e) {
            throw new FailedToReadRemoteException("Сервер не может получить данные от Клиента. Он сам виновать.");
        }
        String result = UTF_8.decode(byteBuffer).toString();
        System.out.println(result + " - from server");
        giveResponse(result + " - from server");
        byteBuffer.clear();
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

    public void activeServer(){
        this.isActive = true;
        System.out.println("Сервер открыт!");
    }
    public void deactivateServer() throws FailedToCloseServerException {
        this.isActive = false;
        try {
            server.close();
        } catch (IOException e) {
            throw new FailedToCloseServerException("Возникла непредвиденная ошибка при закрытии сервера");
        }
        System.out.println("Сервер закрыт!");
        System.exit(0);

    }
}
