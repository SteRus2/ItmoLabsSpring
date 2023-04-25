package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.client.Client;
import us.obviously.itmo.prog.client.exceptions.FailedToReadRemoteException;
import us.obviously.itmo.prog.client.exceptions.FailedToSentRequestsException;
import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.common.action_models.ResponseModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.serializers.ResponseSerializer;
import us.obviously.itmo.prog.common.serializers.Serializer;
import us.obviously.itmo.prog.server.exceptions.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

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
        String body = null;
        try {
            body = this.request.serialize(arguments);
        } catch (FailedToDumpsEx e) {
            //TODO exception
        }
        try {
            client.request(new Request(this.name, body));
        } catch (FailedToSentRequestsException e) {
            throw new RuntimeException(e);
        }
        String buffer = null;
        try {
            buffer = client.waitResponse();
        } catch (FailedToReadRemoteException e) {
            throw new RuntimeException(e);
        }
        System.out.println(3);
        var responseBody = buffer;
        ResponseModel response = null;
        try {
            response = new ResponseSerializer().parse(responseBody);
        } catch (IncorrectValuesTypeException e) {
            throw new RuntimeException(e);
        } catch (IncorrectValueException e) {
            throw new RuntimeException(e);
        } catch (CantParseDataException e) {
            throw new RuntimeException(e);
        }
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
        }
    }

    public Response run(DataCollection dataCollection, String arguments) throws IncorrectValuesTypeException, IncorrectValueException, CantParseDataException, UsedKeyException, FileNotWritableException, FailedToDumpsEx, CantWriteDataException, NoSuchIdException {
        T args = this.request.parse(arguments);
        return this.execute(dataCollection, args);
    }

    abstract public Response execute(DataCollection dataCollection, T arguments) throws UsedKeyException, NoSuchIdException, FileNotWritableException, FailedToDumpsEx, CantWriteDataException;

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
