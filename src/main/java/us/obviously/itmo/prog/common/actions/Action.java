package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.client.Client;
import us.obviously.itmo.prog.client.exceptions.FailedToReadRemoteException;
import us.obviously.itmo.prog.client.exceptions.FailedToSentRequestsException;
import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.common.data.LocalDataCollection;
import us.obviously.itmo.prog.common.serializers.Serializer;
import us.obviously.itmo.prog.server.exceptions.*;

import java.io.IOException;

public abstract class Action<T, D> {
    private final Serializer<T> request;
    private final Serializer<D> response;
    private final String name;

    public Action(String name, Serializer<T> request, Serializer<D> response) {
        this.name = name;
        this.request = request;
        this.response = response;
    }

    public D send(Client client, T arguments) {
        byte[] body = null;
        try {
            body = this.request.serialize(arguments);
        } catch (FailedToDumpsEx e) {
            //TODO exception
        }
        try {
            client.request(new Request(this.name, body));
        } catch (FailedToSentRequestsException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {

        }
        Response response1;
        try {
            response1 = client.waitResponse();
        } catch (FailedToReadRemoteException e) {
            throw new RuntimeException(e);
        }
        try {
            return response.parse(response1.getBody());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        /*try {
            var response1 = client.waitResponse();
        } catch (FailedToReadRemoteException e) {
            throw new RuntimeException(e);
        }
        System.out.println(3);
        var responseBody = buffer;

        if (response.status() == ResponseStatus.BAD_REQUEST) {
            throw new RuntimeException("BAD REQUEST");
        }
        if (response.status() == ResponseStatus.FORBIDDEN) {
            throw new RuntimeException("FORBIDDEN");
        }
        if (response.status() == ResponseStatus.UNAUTHORIZED) {
            throw new RuntimeException("UNAUTHORIZED");
        }
        if (response.status() == ResponseStatus.NOT_FOUND) {
            throw new RuntimeException("NOT_FOUND");
        }
        if (response.status() == ResponseStatus.PAYMENT_REQUIRED) {
            throw new RuntimeException("PAYMENT_REQUIRED");
        }
        if (response.status() == ResponseStatus.SERVER_ERROR) {
            throw new RuntimeException("SERVER_ERROR");
        }
        if (!Objects.equals(response.command(), this.name)) {
            throw new RuntimeException("NOT APPROPROAAPROPIATE COMMAND");
        }
        try {
            return this.getResponse().parse(response.body());
        } catch (IncorrectValuesTypeException e) {
            throw new RuntimeException(e);
        } catch (IncorrectValueException e) {
            throw new RuntimeException(e);
        } catch (CantParseDataException e) {
            throw new RuntimeException(e);
        }*/
    }

    public Response run(LocalDataCollection dataCollection, byte[] arguments) throws IncorrectValuesTypeException, IncorrectValueException, CantParseDataException, UsedKeyException, FileNotWritableException, IOException, CantWriteDataException, NoSuchIdException, ClassNotFoundException {
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
}
