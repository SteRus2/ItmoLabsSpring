package us.obviously.itmo.prog.server.database;

import us.obviously.itmo.prog.server.MainServer;
import us.obviously.itmo.prog.server.exceptions.CantFindFileException;
import us.obviously.itmo.prog.server.exceptions.FailedToConnectToDatabaseException;
import us.obviously.itmo.prog.server.exceptions.IgnoredException;
import us.obviously.itmo.prog.server.exceptions.MissingPropertyException;
import us.obviously.itmo.prog.server.net.Server;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class DatabaseHandler {
    private Connection connection;
    String url;
    String user;
    String password;
    private Properties dataBaseInfo = new Properties();

    private final Logger databaseHandlerLogger;

    {
        databaseHandlerLogger = Logger.getLogger(DatabaseHandler.class.getName());
    }

    public DatabaseHandler() throws MissingPropertyException, FailedToConnectToDatabaseException, CantFindFileException {
        try {
            dataBaseInfo.load(new FileInputStream(MainServer.propertiesSrc));
        } catch (IOException e) {
            throw new CantFindFileException("Файл конфигурации не найден");
        }
        initParameters();
        if (url == null){
            throw new MissingPropertyException("url");
        }
        if (user == null){
            throw new MissingPropertyException("user");
        }
        if (password == null){
            throw new MissingPropertyException("password");
        }
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new FailedToConnectToDatabaseException(e.getMessage());
        }
        databaseHandlerLogger.info("Соединение с базой данных установлено");
    }
    public Connection getConnection() {
        return connection;
    }
    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }
    private void initParameters(){
        url = dataBaseInfo.getProperty("url");
        user = dataBaseInfo.getProperty("user");
        password = dataBaseInfo.getProperty("password");
    }
    public void closeConnection() throws IgnoredException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new IgnoredException();
        }
        databaseHandlerLogger.info("Соединение с базой данных прервано");
    }
}