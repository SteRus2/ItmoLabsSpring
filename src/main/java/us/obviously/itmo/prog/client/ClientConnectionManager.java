package us.obviously.itmo.prog.client;

import us.obviously.itmo.prog.common.ConnectionManager;

public interface ClientConnectionManager extends ConnectionManager {
    void waitResponse();
    void request();
}
