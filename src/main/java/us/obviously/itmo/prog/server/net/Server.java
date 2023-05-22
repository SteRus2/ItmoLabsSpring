package us.obviously.itmo.prog.server.net;


import us.obviously.itmo.prog.client.console.ConsoleColor;
import us.obviously.itmo.prog.client.exceptions.FailedToReadRemoteException;
import us.obviously.itmo.prog.client.exceptions.FailedToSentRequestsException;
import us.obviously.itmo.prog.common.actions.Request;
import us.obviously.itmo.prog.common.actions.Response;
import us.obviously.itmo.prog.common.data.LocalDataCollection;
import us.obviously.itmo.prog.server.ActionManager;
import us.obviously.itmo.prog.server.database.DatabaseManager;
import us.obviously.itmo.prog.server.exceptions.*;
import us.obviously.itmo.prog.server.serverCommands.ServerCommand;
import us.obviously.itmo.prog.server.serverCommands.ServerCommandManager;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server implements ServerConnectionManager {
    public static Logger logger;
    private final Scanner serverCommandReader;
    public final LocalDataCollection data;
    private final Selector selector;
    private ServerSocketChannel server;
    private SocketAddress address;
    private boolean isActive;
    private Set<SelectionKey> selectionKeySet;
    private ServerCommandManager serverCommandManager;
    private ActionManager actionManager;
    private Map<SocketChannel, ArrayList<Request>> clientSocketMap;
    private DatabaseManager databaseManager;


    {
        logger = Logger.getLogger(Server.class.getName());
        logger.setLevel(Level.FINE);
        ConsoleColor.initColors();
        serverCommandReader = new Scanner(System.in);
        clientSocketMap = new HashMap<>();
    }

    public Server(LocalDataCollection dataCollection, int port, DatabaseManager databaseManager) throws FailedToStartServerException {
        this.databaseManager = databaseManager;
        this.data = dataCollection;
        try {
            selector = Selector.open();
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.register(selector, SelectionKey.OP_ACCEPT);
            activeServer();
            serverCommandManager = new ServerCommandManager(this);
            actionManager = new ActionManager();
        } catch (ClosedChannelException e) {
            throw new FailedToStartServerException("Возникла ошибка при старте сервера, сетевой канал закрыт, попробуйте позже");
        } catch (IOException e) {
            throw new FailedToStartServerException("Возникла ошибка при старте сервера, пожалуйста, попробуйте позднее");
        }
        try {
            address = new InetSocketAddress(port);
            server.bind(address);
        } catch (IOException e) {
            throw new FailedToStartServerException("Данный порт занят");
        }
        new Thread() {
            @Override
            public void run() {
                while (serverCommandReader.hasNextLine()) {
                    String commandString = serverCommandReader.nextLine();
                    ServerCommand command = serverCommandManager.getCommand(commandString);
                    if (command == null) {
                        logger.info("Команды сервера не существует");
                    } else {
                        command.execute();
                    }
                }
            }
        }.start();
    }

    @Override
    public void run() {
        while (isActive) {
            try {
                selector.select();
                selectionKeySet = selector.selectedKeys();
                for (var iter = selectionKeySet.iterator(); iter.hasNext(); ) {
                    SelectionKey key = iter.next();
                    iter.remove();
                    if (key.isValid()) {
                        if (key.isAcceptable()) {
                            acceptClient(key);
                            logger.info("Клиенты: " + clientSocketMap.toString());
                        }
                        /*if (key.isReadable()) {
                            try {
                                SocketChannel socketChannel = (SocketChannel) key.channel();
                                Request request = readRequest(socketChannel);
                                if (clientSocketMap.containsKey((SocketChannel) key.channel())) {
                                    clientSocketMap.get((SocketChannel) key.channel()).add(request);
                                    socketChannel.register(key.selector(), SelectionKey.OP_WRITE);
                                }
                                logger.info("запрос от клиента ( " + ((SocketChannel) key.channel()).getRemoteAddress() + " ), Запрос: " + request.toString());
                            } catch (IOException e){
                                logger.info("Клиент отключен " + ((SocketChannel) key.channel()).getRemoteAddress());
                                ((SocketChannel) key.channel()).socket().close();
                                key.cancel();
                                selectionKeySet.remove(key);
                                continue;
                            }
                        }
                        if (key.isWritable()) {
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            if (clientSocketMap.containsKey(socketChannel)) {
                                var requests = clientSocketMap.get(socketChannel);
                                Request request = requests.get(requests.size() - 1);
                                requests.remove(requests.size() - 1);
                                var action = actionManager.getAction(request.getCommand());
                                Response response;
                                if (action == null) {
                                    response = new Response("Действие не найдено", ResponseStatus.NOT_FOUND);
                                } else {
                                    response = action.run(data, request.getBody(), new UserInfo(request.getLogin(), request.getPassword()),databaseManager);
                                }
                                logger.info("Ответ клиенту ( " + socketChannel.getRemoteAddress() + " ), ответ " + response.toString());
                                giveResponse(response, socketChannel);
                                if (requests.isEmpty()) {
                                    socketChannel.register(key.selector(), OP_READ);
                                }
                            }
                        }*/
                    } else {
                        key.cancel();
                    }
                }
            } catch (FailedToAcceptClientException e) {
                logger.warning("Не получилось присоединить клиента: " + e.getMessage());
            } catch (IOException e) {
                logger.warning("Клиент отключен " + Arrays.toString(e.getStackTrace()));
            } /*catch (ClassNotFoundException e) {
                logger.warning("Не удалось получить Приказ клиента");
            } catch (UsedKeyException | CantWriteDataException | NoSuchIdException | CantParseDataException |
                     FileNotWritableException | IncorrectValueException | IncorrectValuesTypeException e) {
                logger.warning("Ошибка при выполнении запроса");
            }*/
        }
    }

    private void acceptClient(SelectionKey selectionKey) throws FailedToAcceptClientException {
        var clientSocket = (ServerSocketChannel) selectionKey.channel();
        try {
            var client = clientSocket.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new ClientConnectionHandler(client, data, databaseManager).run();
                }
            }).start();
            clientSocketMap.put(client, new ArrayList<>());
            logger.info("Новый клиент: " + client.getRemoteAddress());
        } catch (IOException e) {
            throw new FailedToAcceptClientException("Во время соединения с клиентом произошла ошибка");
        }
    }


    @Override
    public ByteBuffer read() throws IOException {

        return ByteBuffer.allocate(1024);
    }

    @Override
    public void write(ByteBuffer data) throws IOException {

    }

    @Override
    public void waitRequest() throws FailedToReadRemoteException, FailedToSentRequestsException {

    }

    @Override
    public void giveResponse(Response response, SocketChannel socketChannel) throws IOException {

    }

    public void activeServer() {
        this.isActive = true;
        logger.info("Сервер открыт!");
    }

    public void deactivateServer() throws FailedToCloseServerException {
        this.isActive = false;
        try {
            server.close();
        } catch (IOException e) {
            throw new FailedToCloseServerException("Возникла непредвиденная ошибка при закрытии сервера");
        }
        logger.info("Сервер закрыт!");
        System.exit(0);
    }
}

