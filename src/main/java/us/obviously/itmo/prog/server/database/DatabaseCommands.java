package us.obviously.itmo.prog.server.database;

public final class DatabaseCommands {
    public static final String getAll = "select * from study_group join persons on study_group.group_admin = persons.person_id";
    public static final String insertUser = "insert into USERS(login, password, salt) values(?, ?, ?);";
    public static final String getUser = "select * from USERS where login = ?;";
    /*CREATE TABLE IF NOT EXISTS USERS (
         login TEXT PRIMARY KEY,
         password TEXT NOT NULL,
         salt TEXT NOT NULL
    );*/
}
