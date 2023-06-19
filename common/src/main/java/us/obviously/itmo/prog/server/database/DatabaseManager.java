package us.obviously.itmo.prog.server.database;

import us.obviously.itmo.prog.UserInfo;
import us.obviously.itmo.prog.UserInfoExplicit;
import us.obviously.itmo.prog.action_models.KeyGroupModel;
import us.obviously.itmo.prog.action_models.UserModel;
import us.obviously.itmo.prog.model.*;
import us.obviously.itmo.prog.server.database.security.MD2Secure;
import us.obviously.itmo.prog.server.database.security.SecureControl;
import us.obviously.itmo.prog.server.exceptions.*;
import us.obviously.itmo.prog.server.net.AuthorizedState;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.logging.Logger;

public class DatabaseManager {
    private final Logger databaseLogger;
    private final String PEPPER = "}]<wzk}n";
    private final SecureControl secureControl;
    private DatabaseHandler databaseHandler;

    {
        secureControl = new MD2Secure();
        databaseLogger = Logger.getLogger(DatabaseManager.class.getName());
    }

    public DatabaseManager(String propertiesSrc) {
        try {
            databaseHandler = new DatabaseHandler(propertiesSrc);

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
            String initSqlTables = "initTables.sql";
            sqlTables = new String(Files.readAllBytes(Paths.get(initSqlTables)));
            String initSqlTypes = "initTypes.sql";
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

//        System.out.println(resultSet.getString("person_name"));

        while (resultSet.next()) {
            var localPerson = new Person(
                    resultSet.getString("person_name"),
                    resultSet.getTimestamp("birthday").toLocalDateTime().atZone(ZoneId.systemDefault()),
                    (resultSet.getString("eye_color") == null) ? null : Color.valueOf(resultSet.getString("eye_color")),
                    (resultSet.getString("hair_color") == null) ? null : Color.valueOf(resultSet.getString("hair_color")),
                    (resultSet.getString("nationality") == null) ? null : Country.valueOf(resultSet.getString("nationality")));
            var studyGroup = new StudyGroup(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    new Coordinates(resultSet.getLong("coordinates_x"), (resultSet.getFloat("coordinates_y"))),
                    resultSet.getDate("creation_date"),
                    resultSet.getInt("students_count"),
                    FormOfEducation.valueOf(resultSet.getString("form_of_education")),
                    Semester.valueOf(resultSet.getString("semester_enum")),
                    localPerson,
                    resultSet.getInt("owner_id"),
                    resultSet.getString("login"));
            localData.put(resultSet.getInt("id"), studyGroup);
        }
        return localData;
    }

    public Integer insertItem(KeyGroupModel arguments, UserInfo userInfo) throws SQLException {
        try {
            PreparedStatement preparedStatement;
            try {
                preparedStatement = databaseHandler.getPreparedStatement(DatabaseCommands.insertStudyGroup);
            } catch (SQLException e) {
                databaseLogger.severe("Ошибка баз данных: " + e.getMessage());
                throw e;
            }
            var group = arguments.getStudyGroup();
            var localPerson = group.getPerson();
            preparedStatement.setString(1, localPerson.getName());
            preparedStatement.setTimestamp(2, Timestamp.from(localPerson.getBirthday().toInstant()));
            preparedStatement.setObject(3, localPerson.getEyeColor(), Types.OTHER);
            preparedStatement.setObject(4, localPerson.getHairColor(), Types.OTHER);
            preparedStatement.setObject(5, localPerson.getNationality(), Types.OTHER);
            var GROUP_INDEX_OFFSET = 5;
            preparedStatement.setString(GROUP_INDEX_OFFSET + 1, group.getName());
            preparedStatement.setLong(GROUP_INDEX_OFFSET + 2, group.getCoordinates().getX());
            if (group.getCoordinates().getY() != null) {
                preparedStatement.setFloat(GROUP_INDEX_OFFSET + 3, group.getCoordinates().getY());
            } else {
                preparedStatement.setObject(GROUP_INDEX_OFFSET + 3, null);
            }
            preparedStatement.setTimestamp(GROUP_INDEX_OFFSET + 4, new Timestamp(group.getCreationDate().getTime()));
            if (group.getStudentsCount() != null) {
                preparedStatement.setInt(GROUP_INDEX_OFFSET + 5, group.getStudentsCount());
            } else {
                preparedStatement.setObject(GROUP_INDEX_OFFSET + 5, null);
            }

            preparedStatement.setObject(GROUP_INDEX_OFFSET + 6, group.getFormOfEducation(), Types.OTHER);
            preparedStatement.setObject(GROUP_INDEX_OFFSET + 7, group.getSemesterEnum(), Types.OTHER);
            preparedStatement.setInt(GROUP_INDEX_OFFSET + 8, userInfo.getId());

            ResultSet groupResultSet = preparedStatement.executeQuery();
            if (!groupResultSet.next()) {
                databaseLogger.warning("Ошибка при добавлении");
                return -1;
            }
            databaseLogger.info("Объект группы добавлен");

            return groupResultSet.getInt(1);
        } catch (SQLException e) {
            databaseLogger.severe("ОШИБКА: " + e.getMessage());
            throw e;
        }
    }

    public void closeConnection() {
        try {
            databaseHandler.closeConnection();
        } catch (IgnoredException ignored) {

        }
    }

    public UserInfo signIn(UserModel userModel) throws UserDoesNotExistException, WrongPasswordException {
        String username = userModel.getUsername();
        try {
            PreparedStatement preparedStatement = getDatabaseHandler().getPreparedStatement(DatabaseCommands.getUser);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String salt = resultSet.getString("salt");
                int id = resultSet.getInt("id");
                if (secureControl.getHashCode(PEPPER + userModel.getPassword() + salt).equals(resultSet.getString("password"))) {
                    databaseLogger.info("Пользователь: (" + userModel.getUsername() + ") вошел в аккаунт");
                    return new UserInfoExplicit(id, userModel.getUsername(), userModel.getPassword());
                } else {
                    throw new WrongPasswordException("Пароль неверный");
                }
            } else {
                throw new UserDoesNotExistException("Пользователь не найден");
            }

        } catch (SQLException e) {
            throw new UserDoesNotExistException("Ошибка запроса");
        }
    }

    /**
     * @param userModel логин, пароль
     * @return Статус успешности авторизации
     * @deprecated
     */
    public AuthorizedState checkUser(UserModel userModel) {
        String username = userModel.getUsername();
        try {
            PreparedStatement preparedStatement = getDatabaseHandler().getPreparedStatement(DatabaseCommands.getUser);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String salt = resultSet.getString("salt");
                if (secureControl.getHashCode(PEPPER + userModel.getPassword() + salt).equals(resultSet.getString("password"))) {
                    databaseLogger.info("Пользователь: (" + userModel.getUsername() + ") вошел в аккаунт");
                    return AuthorizedState.OK;
                } else {
                    return AuthorizedState.INCORRECT_PASSWORD;
                }
            } else {
                return AuthorizedState.NOT_FOUND_LOGIN;
            }

        } catch (SQLException e) {
            return AuthorizedState.NOT_FOUND_LOGIN;
        }
    }

    public UserInfo registerUser(UserModel userModel) throws FailedToRegisterUserException {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = databaseHandler.getPreparedStatement(DatabaseCommands.insertUser);
        } catch (SQLException e) {
            databaseLogger.severe("Невозможно зарегистрировать пользователя, ошибка базы данных: " + e.getMessage());
            throw new FailedToRegisterUserException("Временная ошибка базы данных, регистрация пользователя невозможна");
        }

        try {
            String salt = secureControl.getSalt();
            preparedStatement.setString(1, userModel.getUsername());
            preparedStatement.setString(2, secureControl.getHashCode(PEPPER + userModel.getPassword() + salt));
            preparedStatement.setString(3, salt);
            ResultSet result = preparedStatement.executeQuery();
            databaseLogger.info("Пользователь: (" + userModel.getUsername() + ") зарегистрирован");
            if (!result.next()) {
                databaseLogger.warning("Ошибка при добавлении");
                throw new FailedToRegisterUserException("Пользователь с таким именем уже существует, выберите другое имя");
            }
            int id = result.getInt("id");
            return new UserInfoExplicit(id, userModel.getUsername(), userModel.getPassword());
        } catch (SQLException e) {
            databaseLogger.warning("Регистрация пользователя не удалась: " + e.getMessage());
            throw new FailedToRegisterUserException("Пользователь с таким именем уже существует, выберите другое имя");
        }
    }

    public boolean checkUserObject(int key, int userId) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = databaseHandler.getPreparedStatement(DatabaseCommands.checkUserObject);
        } catch (SQLException e) {
            databaseLogger.severe("Невозможно найти объект пользователя, ошибка базы данных: " + e.getMessage());
            return false;
        }
        try {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            databaseLogger.severe("Ошибка базы данных: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean updateItem(StudyGroup group, int key, int userId) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getDatabaseHandler().getPreparedStatement(DatabaseCommands.updateUserObject);
            preparedStatement.setString(1, group.getName());
            preparedStatement.setLong(2, group.getCoordinates().getX());
            if (group.getCoordinates().getY() != null) {
                preparedStatement.setFloat(3, group.getCoordinates().getY());
            } else {
                preparedStatement.setObject(3, null);
            }
            preparedStatement.setTimestamp(4, new Timestamp(group.getCreationDate().getTime()));
            if (group.getStudentsCount() != null) {
                preparedStatement.setInt(5, group.getStudentsCount());
            } else {
                preparedStatement.setObject(5, null);
            }

            preparedStatement.setObject(6, group.getFormOfEducation(), Types.OTHER);
            preparedStatement.setObject(7, group.getSemesterEnum(), Types.OTHER);
            preparedStatement.setInt(8, userId);
            preparedStatement.setInt(9, key);
            Person localPerson = group.getPerson();
            preparedStatement.setString(10, localPerson.getName());
            preparedStatement.setTimestamp(11, Timestamp.from(localPerson.getBirthday().toInstant()));
            preparedStatement.setObject(12, localPerson.getEyeColor(), Types.OTHER);
            preparedStatement.setObject(13, localPerson.getHairColor(), Types.OTHER);
            preparedStatement.setObject(14, localPerson.getNationality(), Types.OTHER);
            preparedStatement.setInt(15, key);

            preparedStatement.executeUpdate();

            databaseLogger.info("Объект группы Обновлен");
        } catch (SQLException e) {
            databaseLogger.severe("Ошибка базы данных: " + e.getMessage());
            return false;
        }
        return true;
    }

    public void removeAllUserObjects(int userId) throws SQLException {
        try {
            PreparedStatement preparedStatement = getDatabaseHandler().getPreparedStatement(DatabaseCommands.removeUserObjects);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeQuery();
            databaseLogger.info("Все объекты пользователя ( " + userId + " ) удалены");
        } catch (SQLException e) {
            databaseLogger.severe("Ошибка баз данных: " + e.getMessage());
            throw e;
        }
    }

    public void removeGreaterUserObjects(int key, int userId) throws SQLException {
        try {
            PreparedStatement preparedStatement = getDatabaseHandler().getPreparedStatement(DatabaseCommands.removeGreaterUserObject);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, key);
            preparedStatement.executeQuery();
            databaseLogger.info("Объекты пользователя ( " + userId + " ) удалены");
        } catch (SQLException e) {
            databaseLogger.severe("Ошибка баз данных: " + e.getMessage());
            throw e;
        }
    }

    public void removeLowerUserObjects(int key, int userId) throws SQLException {
        try {
            PreparedStatement preparedStatement = getDatabaseHandler().getPreparedStatement(DatabaseCommands.removeLowerUserObject);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, key);
            preparedStatement.executeQuery();
            databaseLogger.info("Все объекты пользователя ( " + userId + " ) удалены");
        } catch (SQLException e) {
            databaseLogger.info("Объекты пользователя ( " + userId + " ) удалены");
            throw e;
        }
    }

    public void removeUserObject(int key, int userId) throws SQLException {
        try {
            PreparedStatement preparedStatement = getDatabaseHandler().getPreparedStatement(DatabaseCommands.removeUserObject);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, key);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            databaseLogger.severe("Ошибка баз данных: " + e.getMessage());
            throw e;
        }
    }
}
