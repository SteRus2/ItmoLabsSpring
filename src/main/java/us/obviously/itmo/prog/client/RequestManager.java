package us.obviously.itmo.prog.client;

import us.obviously.itmo.prog.client.exceptions.FailedToReadRemoteException;
import us.obviously.itmo.prog.common.actions.Request;
import us.obviously.itmo.prog.common.actions.Response;
import us.obviously.itmo.prog.common.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.serializers.Serializer;

import java.io.IOException;

public class RequestManager<T, D> {
    private final Serializer<T> requestSer = new Serializer<>();
    private final Serializer<D> responseSer = new Serializer<>();

    public void send(Client client, T arguments, String commandName) {
        byte[] body = null;
        body = this.requestSer.serialize(arguments);
        //System.out.println(Arrays.toString(body));
        try {
            client.request(new Request(commandName, body, client.getLogin(), client.getPassword()));
        } catch (IOException e) {
            try {
                client.connect(client.getPort());
                client.request(new Request(commandName, body, client.getLogin(), client.getPassword()));
            } catch (IOException ignored) {
            }
        }
    }

    public D recieve(Client client) throws BadRequestException, FailedToReadRemoteException {
        Response response1 = client.waitResponse();
        try {
            switch (response1.getStatus()) {
                case OK, CREATED -> {
                    return responseSer.parse(response1.getBody());
                }
            }
            var errorSerializer = new Serializer<String>();
            throw new BadRequestException(errorSerializer.parse(response1.getBody()));
        } catch (IOException e) {
            throw new FailedToReadRemoteException(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

}
