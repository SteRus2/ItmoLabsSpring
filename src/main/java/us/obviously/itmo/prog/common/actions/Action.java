package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.client.Client;
import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.common.action_models.ResponseModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.serializers.ResponseSerializer;
import us.obviously.itmo.prog.common.serializers.Serializer;
import us.obviously.itmo.prog.server.exceptions.*;

import java.io.IOException;
import java.nio.ByteBuffer;
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
            throw new RuntimeException(e);
        }
        try {
            client.write(ByteBuffer.wrap(body.getBytes()));
        } catch (IOException e) {
            //TODO exception
        }
        ByteBuffer buffer = null;
        try {
            buffer = client.read();
        } catch (IOException e) {
            //TODO exception
        }
        var responseBody = new String(buffer.array());
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

    abstract public D execute(DataCollection dataCollection, T arguments) throws UsedKeyException, NoSuchIdException, FileNotWritableException, FailedToDumpsEx, CantWriteDataException;

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
