package us.obviously.itmo.prog.common.server.net;

import us.obviously.itmo.prog.common.UserInfoExplicit;
import us.obviously.itmo.prog.common.UserInfoPublic;
import us.obviously.itmo.prog.common.actions.Request;
import us.obviously.itmo.prog.common.actions.Response;
import us.obviously.itmo.prog.common.actions.ResponseStatus;
import us.obviously.itmo.prog.common.server.data.LocalDataCollection;
import us.obviously.itmo.prog.common.server.ActionManager;
import us.obviously.itmo.prog.common.server.database.DatabaseManager;
import us.obviously.itmo.prog.common.server.exceptions.ActionDoesNotExistException;
import us.obviously.itmo.prog.common.server.exceptions.InvalidTokenException;

import java.io.*;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static us.obviously.itmo.prog.common.server.net.Server.logger;

public class ClientConnectionHandler {
    private static final int RESPONSES_POOL_SIZE = 4;
    private static final ExecutorService requestsService;
    private static final ExecutorService processingService;
    private static final ExecutorService responsesService;

    static {
        requestsService = Executors.newCachedThreadPool();
        processingService = Executors.newCachedThreadPool();
        responsesService = Executors.newFixedThreadPool(RESPONSES_POOL_SIZE);
    }

    private final int DATA_SIZE = 15000;
    private final SocketChannel socketChannel;
    private final ActionManager actionManager;
    private final LocalDataCollection data;
    private final DatabaseManager databaseManager;
    private String remoteAddress;
    private UserInfoExplicit authorizedUserInfo; // TODO: использовать


    public ClientConnectionHandler(SocketChannel socketChannel, LocalDataCollection data, DatabaseManager databaseManager) {
        this.socketChannel = socketChannel;
        this.data = data;
        this.databaseManager = databaseManager;
        actionManager = new ActionManager();
        try {
            remoteAddress = socketChannel.getRemoteAddress().toString();
            socketChannel.configureBlocking(true);
        } catch (IOException ignored) {
            remoteAddress = "Не определен";
        }
    }

    public void  run() {
        requestsService.submit(() -> {
            while (true) {
                try {
                    // Получение запроса
                    Request request = readRequest(socketChannel);
                    logger.info("запрос от клиента ( " + socketChannel.getRemoteAddress() + " ), Запрос: " + request.toString());

                    processingService.submit(() -> processRequest(request));
                } catch (IOException e) {
                    logger.info("Клиент отключен " + remoteAddress);
                    return;
                } catch (ClassNotFoundException ignored) {
                }
            }
        });
    }

    public void processRequest(Request request) {

        try {        // Обработка запроса
            Response response;
            try {
                var action = actionManager.getAction(request.getCommand());
                String REGISTER_UNIQUE_COMMAND = "register";
                String LOGIN_UNIQUE_COMMAND = "login";
                if (request.getCommand().equals(LOGIN_UNIQUE_COMMAND)) {
                    response = action.run(data, request.getId(), request.getBody(), null, databaseManager);
                } else if (request.getCommand().equals(REGISTER_UNIQUE_COMMAND)) {
                    response = action.run(data, request.getId(), request.getBody(), null, databaseManager);
                } else if (request.getPassword() != null || request.getLogin() != null) {
                    response = new Response(request.getId(), "API обновился, используйте токен вместо логина и пароля", ResponseStatus.UNAUTHORIZED);
                } else if (request.getAuthToken() == null) {
                    response = new Response(request.getId(), "Не передан токен", ResponseStatus.UNAUTHORIZED);
                } else {
                    var jwtValidator = new JwtValidator();
                    var token = jwtValidator.validate(request.getAuthToken());
                    var expiresAt = token.getExpiresAtAsInstant();
                    if (Instant.now().isAfter(expiresAt)) {
                        response = new Response(request.getId(), "Токен спёкся", ResponseStatus.UNAUTHORIZED);
                    } else {
                        int id = Integer.parseInt(token.getClaim("id").asString());
                        var username = token.getClaim("username").asString();
                        response = action.run(data, request.getId(), request.getBody(), new UserInfoPublic(id, username), databaseManager);
                    }
                }

                //Отправка ответов клиенту
                logger.info("Ответ клиенту ( " + socketChannel.getRemoteAddress() + " ), ответ " + response.toString());
            } catch (ActionDoesNotExistException e) {
                response = new Response(request.getId(), "Действие не сертифицировано", ResponseStatus.BAD_REQUEST);
            } catch (InvalidTokenException e) {
                response = new Response(request.getId(), "Невалидный токен. Переавторизиацрауйтесь", ResponseStatus.UNAUTHORIZED);
            }

            Response finalResponse = response;
            responsesService.submit(() -> {
                try {
                    giveResponse(finalResponse, socketChannel);
                } catch (IOException e) {
                    logger.info("Клиент отключен " + remoteAddress);
                }
            });
        } catch (IOException e) {
            logger.info("Клиент отключен " + remoteAddress);
        } catch (ClassNotFoundException ignored) {

        }
    }

    public void giveResponse(Response response, SocketChannel socketChannel) throws IOException {
        byte[] bytes = serializeResponse(response);
        int n = bytes.length;

        int count;
        if (n % DATA_SIZE == 0) {
            count = n / DATA_SIZE;
        } else {
            count = n / DATA_SIZE + 1;
        }
        for (int i = 0; i < count; i++) {
            byte[] buf = Arrays.copyOfRange(bytes, i * DATA_SIZE, (i + 1) * DATA_SIZE + 1);
            if (i == count - 1) {
                buf[buf.length - 1] = 1;
            } else {
                buf[buf.length - 1] = 0;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException ignored) {
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