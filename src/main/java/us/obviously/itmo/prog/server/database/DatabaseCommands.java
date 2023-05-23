package us.obviously.itmo.prog.server.database;

public final class DatabaseCommands {
    public static final String getAll = "select * from study_group join persons on study_group.group_admin = persons.person_id";
    public static final String insertUser = "insert into USERS(login, password, salt) values(?, ?, ?);";
    public static final String getUser = "select * from USERS where login = ?;";
    public static String insertPerson = "insert into PERSONS(person_name, birthday, eye_сolor, hair_сolor, nationality) values (?, ?, ?, ?, ?) returning person_id;";
    public static String insertStudyGroup = "insert into STUDY_GROUP(name, coordinates_x, coordinates_y, creation_date, students_count, form_of_education, semester_enum, group_admin, owner_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?) returning id;";

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
            eye_сolor COLOR NOT NULL,
            hair_сolor COLOR NOT NULL,
            nationality COUNTRY NOT NULL
    );*/
    /*CREATE TABLE IF NOT EXISTS USERS (
         login TEXT PRIMARY KEY,
         password TEXT NOT NULL,
         salt TEXT NOT NULL
    );*/
}
