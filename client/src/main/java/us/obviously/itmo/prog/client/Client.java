package us.obviously.itmo.prog.client;


import us.obviously.itmo.prog.common.actions.Request;
import us.obviously.itmo.prog.common.actions.Response;
import us.obviously.itmo.prog.common.server.exceptions.FailedToCloseConnection;
import us.obviously.itmo.prog.common.server.exceptions.FailedToConnectToServerException;
import us.obviously.itmo.prog.common.server.exceptions.FailedToReadRemoteException;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class Client implements ClientConnectionManager {
    private static final int DATA_SIZE = 15000;
    private static Socket connection;
    private final int port;
    private final Map<Integer, RequestManager<?, ?>> requestManagers = new HashMap<>();
    private boolean isActive;
    private int id = 0;
    private String login = null;
    private String password = null;
    private String authToken = null;
    private final List<Response> responses = new ArrayList<>();

    public Client(int port) throws FailedToConnectToServerException {
        this.port = port;
        try {
            connect(port);
        } catch (UnknownHostException e) {
            throw new FailedToConnectToServerException("Неизвестный хост");
        } catch (SocketTimeoutException e) {
            throw new FailedToConnectToServerException("Превышено время ожидания ответа от сервера");
        } catch (IOException e) {
            throw new FailedToConnectToServerException("Не удается подключиться к серверу, попробуйте позже");
        }
    }

    @Override
    public void connect(int port) throws IOException {
        InetAddress host = InetAddress.getLocalHost();
        connection = new Socket();
        InetSocketAddress address = new InetSocketAddress(host, port);
        connection.connect(address, 1000);

    }


    @Override
    public void run() {
        activeClient();
    }

    @Override
    public void write(ByteBuffer data) throws IOException {
        OutputStream os = connection.getOutputStream();
        os.write(data.array());
    }

    @Override
    public ByteBuffer read() throws IOException {
        var bytes = new ArrayList<Byte>();
        InputStream is = connection.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(is, DATA_SIZE * 2);
        byte[] buf = new byte[DATA_SIZE + 1];
        do {
            int check = bufferedInputStream.read(buf);
            if (check == -1) {
                throw new IOException("Ошибка при чтении данных");
            }
            for (int i = 0; i < DATA_SIZE; i++) {
                bytes.add(buf[i]);
            }
        } while (buf[DATA_SIZE] != 1);

        byte[] responseBytes = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            responseBytes[i] = bytes.get(i);
        }
        return ByteBuffer.wrap(responseBytes);
    }

    void activeClient() {
        this.isActive = true;
        System.out.println("Клиент запущен!");
    }

    void deactivateClient() {
        this.isActive = false;
        System.out.println("Клиент закрыт!");
    }

    public void listener() throws FailedToReadRemoteException {
        while (true) {
            ByteBuffer byteBuffer;
            try {
                byteBuffer = read();
            } catch (IOException e) {
                throw new FailedToReadRemoteException("Не удается получить данные с сервера, попробуйте позднее");
            }
            ByteArrayInputStream bis = new ByteArrayInputStream(byteBuffer.array());
            ObjectInputStream objectInputStream;
            Response response;
            try {
                objectInputStream = new ObjectInputStream(bis);
                response = (Response) objectInputStream.readObject();
                responses.add(response);
//                var requestManager = requestManagers.get(response.getRequestId());
                // notify requestManager (*)
            } catch (IOException e) {
                throw new FailedToReadRemoteException(e.getMessage());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Response waitResponse(Integer requestId, RequestManager<?, ?> manager) {
//        requestManagers.put(requestId, manager);
//        while (response_not_found) {
        while (true) {
//            System.out.println(responses);
            try {
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (Response response : responses) {
                if (Objects.equals(response.getRequestId(), requestId)) {
                    responses.remove(response);
                    return response;
                }
            };

//            try {
////                wait(); // while notify (*)
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }
        // TODO: find response
//        return response;
    }

    @Override
    public void request(Request request) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(bos);
        objectOutputStream.writeObject(request);
        connection.getOutputStream().write(bos.toByteArray());
        bos.close();
    }

    @Override
    public void stop() throws FailedToCloseConnection {
        deactivateClient();
        try {
            connection.close();
        } catch (IOException e) {
            throw new FailedToCloseConnection("Закрыть соединение с сервером по какой то причине не получилось, будем вверить что вашего самообладания хватило на то, чтобы закрыть подключение самостоятельно, иначе, будем считать, что подключения уже нет");
        }
    }

    public int getPort() {
        return port;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
