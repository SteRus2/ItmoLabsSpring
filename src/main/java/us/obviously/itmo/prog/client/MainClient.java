package us.obviously.itmo.prog.client;

import us.obviously.itmo.prog.client.console.ConsoleColor;
import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.client.manager.Manager;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.server.exceptions.CantParseDataException;
import us.obviously.itmo.prog.server.exceptions.FileNotReadableException;
import us.obviously.itmo.prog.server.exceptions.IncorrectValuesTypeException;

import java.io.IOException;
import java.util.Scanner;

public class MainClient {
    public static Client client;
    public static int port = 9999;
    public static void main(String[] args) {
        try {
            ConsoleColor.initColors();
            Scanner scanner = new Scanner(System.in);
            DataCollection dataCollection = new RemoteDataCollection(client);
            client = new Client(dataCollection);
            Management manager = new Manager<StudyGroup>(dataCollection, scanner);
            client.run(port);
            manager.run();
            client.stop();
        } catch (IOException e) {
            //TODO make an exception check
        } catch (IncorrectValuesTypeException e) {
            throw new RuntimeException(e);
        } catch (IncorrectValueException e) {
            throw new RuntimeException(e);
        } catch (FileNotReadableException e) {
            throw new RuntimeException(e);
        } catch (CantParseDataException e) {
            throw new RuntimeException(e);
        }
    }
}
