package us.obviously.itmo.prog.common.server.database;

public final class DatabaseCommands {
    public static final String getAll = """
select
    study_group.id as id,
    study_group.name as name,
    study_group.coordinates_x as coordinates_x,
    study_group.coordinates_y as coordinates_y,
    study_group.creation_date as creation_date,
    study_group.students_count as students_count,
    study_group.form_of_education as form_of_education,
    study_group.semester_enum as semester_enum,
    study_group.owner_id as owner_id,
    persons.person_name as person_name,
    persons.birthday as birthday,
    persons.eye_color as eye_color,
    persons.hair_color as hair_color,
    persons.nationality as nationality,
    users.login as login
from study_group join persons on study_group.group_admin = persons.person_id join users on study_group.owner_id = users.id""";
    public static final String insertUser = "insert into USERS(login, password, salt) values(?, ?, ?) returning id;";
    public static final String getUser = "select * from USERS where login = ?;";
    public static final String insertStudyGroup = "with new_person as (insert into PERSONS (person_name, birthday, eye_color, hair_color, nationality) values (?, ?, ?, ?, ?) returning person_id ) insert into STUDY_GROUP (name, coordinates_x, coordinates_y, creation_date, students_count, form_of_education, semester_enum, group_admin, owner_id) values (?, ?, ?, ?, ?, ?, ?, (SELECT person_id FROM new_person), ?) returning id;";
    public static final String checkUserObject = "select * from STUDY_GROUP where (owner_id = ?) and (id = ?);";
    public static final String updateUserObject = "update STUDY_GROUP set (name, coordinates_x, coordinates_y, creation_date, students_count, form_of_education, semester_enum) = (?, ?, ?, ?, ?, ?, ?) where (owner_id = ?) and (id = ?); " +
            "update persons set person_name = ?, birthday = ?, eye_color = ?, hair_color = ?, nationality = ? where person_id = (select group_admin from study_group where id = ?);";
    public static final String removeUserObjects = "delete from STUDY_GROUP where owner_id = ? RETURNING id";
    public static final String removeGreaterUserObject = "delete from STUDY_GROUP where owner_id = ? and id > ? RETURNING id";
    public static final String removeLowerUserObject = "delete from STUDY_GROUP where owner_id = ? and id < ? RETURNING id";
    public static final String removeUserObject = "delete from STUDY_GROUP where owner_id = ? and id = ? RETURNING id";

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
