package us.obviously.itmo.prog.client;


import us.obviously.itmo.prog.client.exceptions.FailedToCloseConnection;
import us.obviously.itmo.prog.client.exceptions.FailedToConnectToServerException;
import us.obviously.itmo.prog.client.exceptions.FailedToReadRemoteException;
import us.obviously.itmo.prog.client.exceptions.FailedToSentRequestsException;
import us.obviously.itmo.prog.common.actions.Request;
import us.obviously.itmo.prog.common.actions.Response;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Client implements ClientConnectionManager {
    private static final int DATA_SIZE = 4096;
    private static InetAddress host;
    private static Socket connection;
    private InputStream is;
    private OutputStream os;
    private InetSocketAddress address;
    private int port;
    private boolean isActive;

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
        host = InetAddress.getLocalHost();
        connection = new Socket();
        address = new InetSocketAddress(host, port);
        connection.connect(address, 1000);
    }


    @Override
    public void run() {
        activeClient();
    }

    @Override
    public void write(ByteBuffer data) throws IOException {
        os = connection.getOutputStream();
        os.write(data.array());
    }

    @Override
    public ByteBuffer read() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(DATA_SIZE * 32);
        is = connection.getInputStream();
        is.read(buffer.array());
        return buffer;
    }

    void activeClient() {
        this.isActive = true;
        System.out.println("Клиент запущен!");
    }

    void deactivateClient() {
        this.isActive = false;
        System.out.println("Клиент закрыт!");
    }

    @Override
    public Response waitResponse() throws FailedToReadRemoteException {
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response.toString());
        return response;
    }

    @Override
    public void request(Request request) throws IOException {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(bos);
        System.out.println(request);
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
}
