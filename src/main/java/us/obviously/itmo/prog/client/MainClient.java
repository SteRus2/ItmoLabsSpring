package us.obviously.itmo.prog.client;

import java.io.IOException;

public class MainClient {
    public static Client client;
    public static int port = 9999;
    public static void main(String[] args) {
        client = new Client();
        try {
            client.run(port);
        } catch (IOException e) {
            //TODO make an exception check
        }
    }
}
