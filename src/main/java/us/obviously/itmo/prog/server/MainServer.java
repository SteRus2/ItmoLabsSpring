package us.obviously.itmo.prog.server;

import java.io.IOException;

public class MainServer {
    public static int port = 9999;
    public static Server server;
    public static void main(String[] args) {
        server = new Server();
        try {
            server.run(port);
        } catch (IOException e) {
            //TODO Exceptions
        }
    }
}
