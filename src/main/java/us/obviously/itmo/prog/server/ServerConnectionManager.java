package us.obviously.itmo.prog.server;

import us.obviously.itmo.prog.common.ConnectionManager;

import java.io.IOException;

public interface ServerConnectionManager extends ConnectionManager {
    void waitRequest() throws IOException;
}
