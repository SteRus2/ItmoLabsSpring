package us.obviously.itmo.prog.client;

import us.obviously.itmo.prog.common.console.ConsoleColor;
import us.obviously.itmo.prog.common.console.Messages;
import us.obviously.itmo.prog.common.server.exceptions.FailedToCloseConnection;
import us.obviously.itmo.prog.common.server.exceptions.FailedToConnectToServerException;
import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.client.manager.Manager;
import us.obviously.itmo.prog.common.server.data.DataCollection;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.common.server.exceptions.FailedToReadRemoteException;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClient {
    public static Client client;
    public static final int port = 11253;
    private static final ExecutorService listenerExecutorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        try {
            ConsoleColor.initColors();
            Scanner scanner = new Scanner(System.in);
            client = new Client(port);
            listenerExecutorService.submit(() -> {
                try {
                    client.listener();
                } catch (FailedToReadRemoteException e) {
                    throw new RuntimeException(e);
                }
            });
            DataCollection dataCollection = new RemoteDataCollection(client);
            Management manager = new Manager<StudyGroup>(dataCollection, scanner);
            client.run();
            manager.run();
            client.stop();
            listenerExecutorService.shutdown();
        } catch (FailedToCloseConnection e) {
            Messages.printStatement("~reПодключение не закрыто, ВНИМАНИЕ, подключение не закрыто: " + e.getMessage() + "~=");
        } catch (FailedToConnectToServerException e) {
            Messages.printStatement("~reПодключиться не удалось: " + e.getMessage() + "~=");
        }
    }
}