CREATE TABLE IF NOT EXISTS USERS (
    login TEXT PRIMARY KEY,
    password TEXT NOT NULL,
    salt TEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS PERSONS(
    person_id SERIAL PRIMARY KEY,
    person_name TEXT NOT NULL CHECK (length(person_name) > 0),
    birthday TIMESTAMP with time zone NOT NULL,
    eye_color COLOR,
    hair_color COLOR,
    nationality COUNTRY
);
CREATE TABLE IF NOT EXISTS STUDY_GROUP (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL CHECK (length(name) > 0),
    coordinates_x NUMERIC NOT NULL,
    coordinates_y NUMERIC CHECK (coordinates_y > -373),
    creation_date TIMESTAMP NOT NULL,
    students_count INTEGER CHECK (students_count > 0),
    form_of_education FORM_OF_EDUCATION NOT NULL,
    semester_enum SEMESTER NOT NULL,
    group_admin INTEGER REFERENCES PERSONS ON DELETE CASCADE,
    owner_id TEXT REFERENCES USERS ON DELETE CASCADE
);