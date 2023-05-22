package us.obviously.itmo.prog.server.database;

import us.obviously.itmo.prog.common.action_models.KeyGroupModel;
import us.obviously.itmo.prog.common.model.*;
import us.obviously.itmo.prog.server.exceptions.CantFindFileException;
import us.obviously.itmo.prog.server.exceptions.FailedToConnectToDatabaseException;
import us.obviously.itmo.prog.server.exceptions.IgnoredException;
import us.obviously.itmo.prog.server.exceptions.MissingPropertyException;
import us.obviously.itmo.prog.common.UserInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.logging.Logger;

public class DatabaseManager {
    private DatabaseHandler databaseHandler;
    private final Logger databaseLogger;
    private final String initSqlTables = "initTables.sql";
    private final String initSqlTypes = "initTypes.sql";

    {
        databaseLogger = Logger.getLogger(DatabaseManager.class.getName());
    }

    public DatabaseManager() {
        try {
            databaseHandler = new DatabaseHandler();

        } catch (MissingPropertyException e) {
            databaseLogger.severe("Нужный аргумент в конфиге не найден " + e.getMessage());
            System.exit(0);
        } catch (FailedToConnectToDatabaseException e) {
            databaseLogger.severe("Не удалось подключиться к базе данных " + e.getMessage());
            System.exit(0);
        } catch (CantFindFileException e) {
            databaseLogger.severe("Файл не найден: " + e.getMessage());
            System.exit(0);
        }
        initTables();
    }

    private void initTables() {
        String sqlTables = null;
        String sqlTypes = null;
        try {
            sqlTables = new String(Files.readAllBytes(Paths.get(initSqlTables)));
            sqlTypes = new String(Files.readAllBytes(Paths.get(initSqlTypes)));
        } catch (IOException e) {
            databaseLogger.severe("Ошибка во время чтения скриптов");
            System.exit(0);
        }
        try {
            databaseHandler.getConnection().prepareStatement(sqlTypes).execute();
        } catch (SQLException e) {
            databaseLogger.warning("Во время инициализации базы данных не удалось обновить структуру: " + e.getMessage());
        }
        try {
            databaseHandler.getConnection().prepareStatement(sqlTables).execute();
        } catch (SQLException e) {
            databaseLogger.severe("Ошибка базы данных: " + e.getMessage());
            System.exit(0);
        }
        databaseLogger.info("База данных проинициализирована");
    }

    public DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }

    public HashMap<Integer, StudyGroup> getData() throws SQLException {
        HashMap<Integer, StudyGroup> localData = new HashMap<>();
        PreparedStatement preparedStatement = databaseHandler.getPreparedStatement(DatabaseCommands.getAll);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            var localPerson = new Person(
                    resultSet.getString("person_name"),
                    resultSet.getTimestamp("birthday").toLocalDateTime().atZone(ZoneId.systemDefault()),
                    Color.valueOf(resultSet.getString("eye_сolor")),
                    Color.valueOf(resultSet.getString("hair_сolor")),
                    Country.valueOf(resultSet.getString("nationality"))
            );
            localData.put(resultSet.getInt("id"), new StudyGroup(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    new Coordinates(
                            resultSet.getLong("coordinates_x"),
                            resultSet.getFloat("coordinates_y")
                    ),
                    resultSet.getDate("creation_date"),
                    resultSet.getInt("students_count"),
                    FormOfEducation.valueOf(resultSet.getString("form_of_education")),
                    Semester.valueOf(resultSet.getString("semester_enum")),
                    localPerson
            ));
        }
        return localData;
    }

    public void insertItem(KeyGroupModel arguments, UserInfo userInfo) {
        
    }

    public void closeConnection() {
        try {
            databaseHandler.closeConnection();
        } catch (IgnoredException e) {

        }
    }
}
