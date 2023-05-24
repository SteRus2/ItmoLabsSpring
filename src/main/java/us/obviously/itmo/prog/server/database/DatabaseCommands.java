package us.obviously.itmo.prog.server.database;

public final class DatabaseCommands {
    public static final String getAll = "select * from study_group join persons on study_group.group_admin = persons.person_id";
    public static final String insertUser = "insert into USERS(login, password, salt) values(?, ?, ?);";
    public static final String getUser = "select * from USERS where login = ?;";
    public static String insertPerson = "insert into PERSONS(person_name, birthday, eye_color, hair_color, nationality) values (?, ?, ?, ?, ?) returning person_id;";
    public static String insertStudyGroup = "insert into STUDY_GROUP(name, coordinates_x, coordinates_y, creation_date, students_count, form_of_education, semester_enum, group_admin, owner_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?) returning id;";
    public static String checkUserObject = "select * from STUDY_GROUP where (owner_id = ?) and (id = ?);";
    public static String updateUserObject = "update STUDY_GROUP set (name, coordinates_x, coordinates_y, creation_date, students_count, form_of_education, semester_enum) = (?, ?, ?, ?, ?, ?, ?) where (owner_id = ?) and (id = ?); " +
            "update persons set person_name = ?, birthday = ?, eye_сolor = ?, hair_сolor = ?, nationality = ? where person_id = (select group_admin from study_group where id = ?);";
    public static String removeUserObjects = "delete from STUDY_GROUP where owner_id = ? RETURNING id";
    public static String removeGreaterUserObject = "delete from STUDY_GROUP where owner_id = ? and id > ? RETURNING id";
    public static String removeLowerUserObject = "delete from STUDY_GROUP where owner_id = ? and id < ? RETURNING id";
    public static String removeUserObject = "delete from STUDY_GROUP where owner_id = ? and id = ? RETURNING id";

    /*CREATE TABLE IF NOT EXISTS STUDY_GROUP (
        id SERIAL PRIMARY KEY,
        name TEXT NOT NULL,
        coordinates_x NUMERIC NOT NULL,
        coordinates_y NUMERIC NOT NULL,
        creation_date TIMESTAMP NOT NULL,
        students_count INTEGER,
        form_of_education FORM_OF_EDUCATION NOT NULL,
        semester_enum SEMESTER NOT NULL,
        group_admin INTEGER REFERENCES PERSONS,
        owner_id TEXT REFERENCES USERS
);/
    /*CREATE TABLE IF NOT EXISTS PERSONS(
            person_id SERIAL PRIMARY KEY,
            person_name TEXT NOT NULL,
            birthday TIMESTAMP NOT NULL,
            eye_color COLOR NOT NULL,
            hair_color COLOR NOT NULL,
            nationality COUNTRY NOT NULL
    );*/
    /*CREATE TABLE IF NOT EXISTS USERS (
         login TEXT PRIMARY KEY,
         password TEXT NOT NULL,
         salt TEXT NOT NULL
    );*/
}
