package us.obviously.itmo.prog.client;

import us.obviously.itmo.prog.client.console.ConsoleColor;
import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.exceptions.FailedToCloseConnection;
import us.obviously.itmo.prog.client.exceptions.FailedToConnectToServerException;
import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.client.manager.Manager;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.server.exceptions.CantFindFileException;
import us.obviously.itmo.prog.server.exceptions.CantParseDataException;
import us.obviously.itmo.prog.server.exceptions.FileNotReadableException;
import us.obviously.itmo.prog.server.exceptions.IncorrectValuesTypeException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class MainClient {
    public static Client client;
    public static int port = 9999;
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
        } catch (CantFindFileException e) {
            Messages.printStatement("~reФайл не найден. Убедитесь в правильности пути и повторите попытку.~=");
        } catch (IncorrectValueException | IncorrectValuesTypeException e) {
            Messages.printStatement("~reНевалидные данные. " + e.getMessage() + "~=");
        } catch (CantParseDataException e) {
            Messages.printStatement("~reФайл нечитаем. " + e.getMessage() + "~=");
        } catch (FileNotReadableException e) {
            Messages.printStatement("~reНет разрешение на чтение файла. Воспользуйтесь командой ~grchmod~=");
        } catch (FailedToCloseConnection e) {
            Messages.printStatement("~reПодключение не закрыто, ВНИМАНИЕ, подключение не закрыто: " + e.getMessage() + "~=");
        } catch (FailedToConnectToServerException e) {
            Messages.printStatement("~reПодключиться не удалось: " + e.getMessage() + "~=");
        }
    }
}
