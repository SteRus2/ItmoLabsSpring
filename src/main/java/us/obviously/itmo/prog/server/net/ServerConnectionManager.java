package us.obviously.itmo.prog.server.net;

import us.obviously.itmo.prog.client.exceptions.FailedToReadRemoteException;
import us.obviously.itmo.prog.client.exceptions.FailedToSentRequestsException;
import us.obviously.itmo.prog.common.ConnectionManager;

import java.io.IOException;

public interface ServerConnectionManager extends ConnectionManager {
    void waitRequest() throws IOException, FailedToReadRemoteException, FailedToSentRequestsException;
    void giveResponse(String response) throws FailedToSentRequestsException;
}
