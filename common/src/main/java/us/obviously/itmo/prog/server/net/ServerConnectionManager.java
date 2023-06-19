package us.obviously.itmo.prog.server.net;

import us.obviously.itmo.prog.ConnectionManager;
import us.obviously.itmo.prog.actions.Response;

import java.nio.channels.SocketChannel;

public interface ServerConnectionManager extends ConnectionManager {
    void waitRequest();

    void giveResponse(Response response, SocketChannel socketChannel);
}
