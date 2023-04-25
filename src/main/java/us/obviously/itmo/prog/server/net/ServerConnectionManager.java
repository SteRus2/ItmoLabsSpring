package us.obviously.itmo.prog.server.net;

import us.obviously.itmo.prog.client.exceptions.FailedToReadRemoteException;
import us.obviously.itmo.prog.client.exceptions.FailedToSentRequestsException;
import us.obviously.itmo.prog.common.ConnectionManager;
import us.obviously.itmo.prog.common.actions.Response;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public interface ServerConnectionManager extends ConnectionManager {
    void waitRequest() throws IOException, FailedToReadRemoteException, FailedToSentRequestsException;
    void giveResponse(Response response, SocketChannel socketChannel) throws FailedToSentRequestsException, IOException;
}
