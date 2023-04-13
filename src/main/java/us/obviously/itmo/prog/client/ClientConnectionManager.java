package us.obviously.itmo.prog.client;

import us.obviously.itmo.prog.client.exceptions.FailedToCloseConnection;
import us.obviously.itmo.prog.client.exceptions.FailedToReadRemoteException;
import us.obviously.itmo.prog.client.exceptions.FailedToSentRequestsException;
import us.obviously.itmo.prog.common.ConnectionManager;

import java.io.IOException;

public interface ClientConnectionManager extends ConnectionManager {
    String waitResponse() throws IOException, FailedToReadRemoteException;
    void request(String request) throws FailedToSentRequestsException;

    void stop() throws IOException, FailedToCloseConnection;
}
