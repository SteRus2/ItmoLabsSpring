package us.obviously.itmo.prog.client;

import us.obviously.itmo.prog.common.ConnectionManager;

import java.io.IOException;

public interface ClientConnectionManager extends ConnectionManager {
    String waitResponse();
    void request(String request);

    void stop() throws IOException;
}
