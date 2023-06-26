package us.obviously.itmo.prog.client;


import us.obviously.itmo.prog.common.actions.Request;
import us.obviously.itmo.prog.common.actions.Response;
import us.obviously.itmo.prog.common.serializers.Serializer;
import us.obviously.itmo.prog.common.server.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.server.exceptions.FailedToReadRemoteException;

import java.io.IOException;

public class DataReceiver<T> {
    private final Serializer<T> responseSer = new Serializer<>();
    private final RequestManager<?, T> manager;
    private Request request;
    private Client client;


    public DataReceiver(Request request, RequestManager<?, T> manager, Client client) {
        this.request = request;
        this.manager = manager;
        this.client = client;
    }

    public T receive() throws BadRequestException, FailedToReadRemoteException {
        Response response = client.waitResponse(request.getId(), this.manager);
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