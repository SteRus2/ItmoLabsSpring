package us.obviously.itmo.prog;

import us.obviously.itmo.prog.actions.Request;
import us.obviously.itmo.prog.actions.Response;
import us.obviously.itmo.prog.server.exceptions.FailedToCloseConnection;
import us.obviously.itmo.prog.server.exceptions.FailedToReadRemoteException;

import java.io.IOException;

public interface ClientConnectionManager extends ConnectionManager {
    Response waitResponse() throws FailedToReadRemoteException;

    void request(Request request) throws IOException;

    void stop() throws FailedToCloseConnection;

    void connect(int port) throws IOException;
}
