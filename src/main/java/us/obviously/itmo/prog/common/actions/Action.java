package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.client.Client;
import us.obviously.itmo.prog.client.exceptions.FailedToReadRemoteException;
import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.common.data.LocalDataCollection;
import us.obviously.itmo.prog.common.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.serializers.Serializer;
import us.obviously.itmo.prog.server.database.DatabaseManager;
import us.obviously.itmo.prog.server.exceptions.*;
import us.obviously.itmo.prog.common.UserInfo;

import java.io.IOException;

public abstract class Action<T, D> {
    private final Serializer<T> request;
    private final Serializer<D> response;
    private final String name;
    private DatabaseManager databaseManager;
    private UserInfo userInfo;

    public Action(String name/*, Serializer<T> request, Serializer<D> response*/) {
        this.name = name;
        this.request = new Serializer<>();
        this.response = new Serializer<>();
    }
    @Deprecated
    public void send(Client client, T arguments) {
        /*try {
            client.connect(client.getPort());
        } catch (IOException e) {
        }*/
        byte[] body = null;
        body = this.request.serialize(arguments);
        //System.out.println(Arrays.toString(body));
        try {
            client.request(new Request(this.name, body));
        } catch (IOException e) {
            try {
                client.connect(client.getPort());
                client.request(new Request(this.name, body));
            } catch (IOException ex) {
            }
        }
    }
    @Deprecated
    public D recieve(Client client) throws BadRequestException, FailedToReadRemoteException {
        Response response1 = client.waitResponse();
        try {
            switch (response1.getStatus()) {
                case OK, CREATED -> {
                    return response.parse(response1.getBody());
                }
            }
            var errorSerializer = new Serializer<String>();
            throw new BadRequestException(errorSerializer.parse(response1.getBody()));
        } catch (IOException e) {
            throw new FailedToReadRemoteException(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Response run(LocalDataCollection dataCollection, byte[] arguments, UserInfo userInfo, DatabaseManager databaseManager) throws IncorrectValuesTypeException, IncorrectValueException, CantParseDataException, UsedKeyException, FileNotWritableException, IOException, CantWriteDataException, NoSuchIdException, ClassNotFoundException {
        this.databaseManager = databaseManager;
        this.userInfo = userInfo;
        T args = this.request.parse(arguments);
        return this.execute(dataCollection, args);
    }

    abstract public Response execute(LocalDataCollection dataCollection, T arguments);

    public Serializer<D> getResponse() {
        return response;
    }

    public Serializer<T> getRequest() {
        return request;
    }

    public String getName() {
        return name;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
