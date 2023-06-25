package us.obviously.itmo.prog.client;

import us.obviously.itmo.prog.common.actions.Request;
import us.obviously.itmo.prog.common.actions.Response;
import us.obviously.itmo.prog.common.serializers.Serializer;
import us.obviously.itmo.prog.common.server.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.server.exceptions.FailedToReadRemoteException;

import java.io.IOException;
import java.util.HashMap;

public class RequestManager<T, D> {
    private final Serializer<T> requestSer = new Serializer<>();
    private final Serializer<D> responseSer = new Serializer<>();
    private Request request;

    public void send(Client client, T arguments, String commandName) {
        byte[] body;
        body = this.requestSer.serialize(arguments);
        request = new Request(commandName, body, client.getAuthToken());
        try {
            client.request(request);
        } catch (IOException e) {
            try {
                client.connect(client.getPort());
                client.request(request);
            } catch (IOException ignored) {
            }
        }
    }

    public D receive(Client client) throws BadRequestException, FailedToReadRemoteException {
        Response response = client.waitResponse(request.getId(), this);
        try {
            switch (response.getStatus()) {
                case OK, CREATED -> {
                    return responseSer.parse(response.getBody());
                }
            }
            var errorSerializer = new Serializer<String>();
            throw new BadRequestException(errorSerializer.parse(response.getBody()));
        } catch (IOException e) {
            throw new FailedToReadRemoteException(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

}
