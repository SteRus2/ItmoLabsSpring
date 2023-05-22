package us.obviously.itmo.prog.server;

import us.obviously.itmo.prog.client.console.ConsoleColor;
import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.common.data.LocalDataCollection;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.server.data.DataStorage;
import us.obviously.itmo.prog.server.database.DatabaseManager;
import us.obviously.itmo.prog.server.exceptions.*;
import us.obviously.itmo.prog.server.net.Server;
import us.obviously.itmo.prog.server.reader.FileFormat;
import us.obviously.itmo.prog.server.reader.FileFormatReader;

import java.sql.SQLException;
import java.util.HashMap;

public class MainServer {
    public static int port = 11253;
    public static Server server;
    private static HashMap<Integer, StudyGroup> initData;
    public static String propertiesSrc;
    static {
        ConsoleColor.initColors();
    }
    public static void main(String[] args) {
        if (args.length != 1) {
            Messages.printStatement("Программа принимает единственный обязательный аргумент - путь к файлу конфигурации.");
            return;
        }
        propertiesSrc = args[0];
        try {
            DatabaseManager databaseManager = new DatabaseManager();

            //initData = databaseManager.getData();
            initData = new FileFormatReader("big-data.xml", FileFormat.XML).getData();
            LocalDataCollection dataCollection = new DataStorage(initData);
            server = new Server(dataCollection, port, databaseManager);
            server.run();
            databaseManager.closeConnection();
        } catch (CantFindFileException e) {
            Messages.printStatement("~reФайл не найден. Убедитесь в правильности пути и повторите попытку.~=");
        } catch (IncorrectValueException | IncorrectValuesTypeException e) {
            Messages.printStatement("~reНевалидные данные. " + e.getMessage() + "~=");
        } catch (CantParseDataException e) {
            Messages.printStatement("~reФайл нечитаем. " + e.getMessage() + "~=");
        } catch (FileNotReadableException e) {
            Messages.printStatement("~reНет разрешение на чтение файла. Воспользуйтесь командой ~grchmod~=");
        } catch (FailedToStartServerException e) {
            Messages.printStatement("~reСервер не запущен: " + e.getMessage() + "~=");
        }
    }
}
