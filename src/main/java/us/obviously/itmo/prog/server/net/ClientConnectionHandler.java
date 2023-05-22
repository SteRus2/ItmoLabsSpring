package us.obviously.itmo.prog.server.net;

import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.common.UserInfo;
import us.obviously.itmo.prog.common.actions.Request;
import us.obviously.itmo.prog.common.actions.Response;
import us.obviously.itmo.prog.common.actions.ResponseStatus;
import us.obviously.itmo.prog.common.data.LocalDataCollection;
import us.obviously.itmo.prog.server.ActionManager;
import us.obviously.itmo.prog.server.database.DatabaseManager;
import us.obviously.itmo.prog.server.exceptions.*;

import java.io.*;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

import static us.obviously.itmo.prog.server.net.Server.logger;

public class ClientConnectionHandler {
    private SocketChannel socketChannel;
    private final int DATA_SIZE = 15000;
    private String remoteAddress;
    private ActionManager actionManager;
    private LocalDataCollection data;
    private DatabaseManager databaseManager;

    public ClientConnectionHandler(SocketChannel socketChannel, LocalDataCollection data, DatabaseManager databaseManager){
        this.socketChannel = socketChannel;
        this.data = data;
        this.databaseManager = databaseManager;
        actionManager = new ActionManager();
        try {
            remoteAddress = socketChannel.getRemoteAddress().toString();
            socketChannel.configureBlocking(true);
        } catch (IOException ignored) {
        }
    }

    public void run() {
        while (true){
            try {
                // Получение запроса
                Request request = readRequest(socketChannel);
                logger.info("запрос от клиента ( " + socketChannel.getRemoteAddress() + " ), Запрос: " + request.toString());
                var action = actionManager.getAction(request.getCommand());

                // Обработка запроса
                Response response;
                if (action == null) {
                    response = new Response("Действие не найдено", ResponseStatus.NOT_FOUND);
                } else {
                    response = action.run(data, request.getBody(), new UserInfo(request.getLogin(), request.getPassword()), null);
                }

                //Отправка ответов клиенту
                logger.info("Ответ клиенту ( " + socketChannel.getRemoteAddress() + " ), ответ " + response.toString());
                giveResponse(response, socketChannel);
            } catch (IOException e) {
                logger.info("Клиент отключен " + remoteAddress);
                return;
            } catch (NoSuchIdException | CantWriteDataException | CantParseDataException |
                     FileNotWritableException | IncorrectValueException | IncorrectValuesTypeException |
                     UsedKeyException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException ignored){

            }
        }
    }

    public void giveResponse(Response response, SocketChannel socketChannel) throws IOException {
        byte[] bytes = serializeResponse(response);
        int n = bytes.length;

        int count;
        if (n % DATA_SIZE == 0){
            count = n / DATA_SIZE;
        } else {
            count = n / DATA_SIZE + 1;
        }
        for (int i = 0; i < count; i++){
            byte[] buf = Arrays.copyOfRange(bytes, i * DATA_SIZE, (i + 1) * DATA_SIZE + 1);
            if (i == count - 1){
                buf[buf.length - 1] = 1;
            } else {
                buf[buf.length - 1] = 0;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            socketChannel.write(ByteBuffer.wrap(buf));
        }
    }


    private Request readRequest(SocketChannel socketChannel) throws IOException, ClassNotFoundException {
        ByteBuffer buf = ByteBuffer.allocate(DATA_SIZE);
        try {
            socketChannel.read(buf);
        } catch (SocketException e) {
            socketChannel.close();
            logger.warning("Клиент отключен " + Arrays.toString(e.getStackTrace()));
        }
        return deserializeRequest(buf);
    }

    private Request deserializeRequest(ByteBuffer buf) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buf.array());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return (Request) objectInputStream.readObject();
    }

    private byte[] serializeResponse(Response response) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(response);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
