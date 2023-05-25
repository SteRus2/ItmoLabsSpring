package us.obviously.itmo.prog.client;

import us.obviously.itmo.prog.client.console.ConsoleColor;
import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.exceptions.FailedToCloseConnection;
import us.obviously.itmo.prog.client.exceptions.FailedToConnectToServerException;
import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.client.manager.Manager;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.model.StudyGroup;

import java.util.Scanner;

public class MainClient {
    public static Client client;
    public static final int port = 11253;

    public static void main(String[] args) {
        try {
            ConsoleColor.initColors();
            Scanner scanner = new Scanner(System.in);
            client = new Client(port);
            DataCollection dataCollection = new RemoteDataCollection(client);
            Management manager = new Manager<StudyGroup>(dataCollection, scanner);
            client.run();
            manager.run();
            client.stop();
        } catch (FailedToCloseConnection e) {
            Messages.printStatement("~reПодключение не закрыто, ВНИМАНИЕ, подключение не закрыто: " + e.getMessage() + "~=");
        } catch (FailedToConnectToServerException e) {
            Messages.printStatement("~reПодключиться не удалось: " + e.getMessage() + "~=");
        }
    }
}
