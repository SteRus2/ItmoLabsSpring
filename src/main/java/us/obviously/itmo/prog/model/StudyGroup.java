package us.obviously.itmo.prog.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class StudyGroup {
    private Integer id; // Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; // Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; // Поле не может быть null
    private java.util.Date creationDate; // Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer studentsCount; // Значение поля должно быть больше 0, Поле может быть null
    private FormOfEducation formOfEducation; // Поле не может быть null
    private Semester semesterEnum; // Поле не может быть null
    private Person groupAdmin; // Поле не может быть null

    public static StudyGroup.Builder newBuilder() {
        return new StudyGroup().new Builder();
    }

    public StudyGroup(){}

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Integer getStudentsCount() {
        return studentsCount;
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    public class Builder {
        public Builder setId(Integer id) {
            StudyGroup.this.id = id;

            return this;
        }

        public StudyGroup build() {
            return StudyGroup.this;
        }
    }
}
