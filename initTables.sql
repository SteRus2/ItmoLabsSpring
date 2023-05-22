CREATE TABLE IF NOT EXISTS USERS (
    login TEXT PRIMARY KEY,
    password TEXT NOT NULL,
    salt TEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS PERSONS(
    person_id SERIAL PRIMARY KEY,
    person_name TEXT NOT NULL,
    birthday TIMESTAMP NOT NULL,
    eye_сolor COLOR NOT NULL,
    hair_сolor COLOR NOT NULL,
    nationality COUNTRY NOT NULL
);
CREATE TABLE IF NOT EXISTS STUDY_GROUP (
    id INTEGER PRIMARY KEY,
    name TEXT NOT NULL,
    coordinates_x NUMERIC NOT NULL,
    coordinates_y NUMERIC NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    students_count INTEGER,
    form_of_education FORM_OF_EDUCATION NOT NULL,
    semester_enum SEMESTER NOT NULL,
    group_admin INTEGER REFERENCES PERSONS,
    owner_id TEXT REFERENCES USERS
);