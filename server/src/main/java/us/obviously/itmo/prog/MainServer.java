package us.obviously.itmo.prog;

import us.obviously.itmo.prog.common.console.ConsoleColor;
import us.obviously.itmo.prog.common.console.Messages;
import us.obviously.itmo.prog.common.server.data.LocalDataCollection;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.common.server.data.DataStorage;
import us.obviously.itmo.prog.common.server.database.DatabaseManager;
import us.obviously.itmo.prog.common.server.exceptions.*;
import us.obviously.itmo.prog.common.server.net.Server;

import java.sql.SQLException;
import java.util.HashMap;

public class MainServer {
    public static final int port = 11253;
    public static Server server;
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
            DatabaseManager databaseManager = new DatabaseManager(propertiesSrc);

            HashMap<Integer, StudyGroup> initData = databaseManager.getData();
            //initData = new FileFormatReader("big-data.xml", FileFormat.XML).getData();
            LocalDataCollection dataCollection = new DataStorage(initData);
            server = new Server(dataCollection, port, databaseManager);
            server.run();
            databaseManager.closeConnection();
        } catch (FailedToStartServerException e) {
            Messages.printStatement("~reСервер не запущен: " + e.getMessage() + "~=");
        } catch (SQLException e) {
            Messages.printStatement("~reОшибка при выполнении запроса: " + e.getMessage() + "~=");
        }
    }
}
