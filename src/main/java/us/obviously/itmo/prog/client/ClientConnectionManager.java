package us.obviously.itmo.prog.client;

import us.obviously.itmo.prog.client.exceptions.FailedToCloseConnection;
import us.obviously.itmo.prog.client.exceptions.FailedToReadRemoteException;
import us.obviously.itmo.prog.common.ConnectionManager;
import us.obviously.itmo.prog.common.actions.Request;
import us.obviously.itmo.prog.common.actions.Response;

import java.io.IOException;

public interface ClientConnectionManager extends ConnectionManager {
    Response waitResponse() throws FailedToReadRemoteException;

    void request(Request request) throws IOException;

    void stop() throws FailedToCloseConnection;

    void connect(int port) throws IOException;
}
