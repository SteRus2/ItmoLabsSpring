package us.obviously.itmo.prog.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Objects;

public class StudyGroup implements Comparable {
    private Integer id; // Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; // Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; // Поле не может быть null
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private java.util.Date creationDate; // Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer studentsCount; // Значение поля должно быть больше 0, Поле может быть null
    private FormOfEducation formOfEducation; // Поле не может быть null
    private Semester semesterEnum; // Поле не может быть null
    private Person groupAdmin; // Поле не может быть null

    public StudyGroup() {
    }

    public static StudyGroup.Builder newBuilder() {
        return new StudyGroup().new Builder();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof StudyGroup)) {
            return 1;
        }
        if (!this.name.equals(((StudyGroup) o).name)) {
            return this.name.compareToIgnoreCase(((StudyGroup) o).name);
        }
        if (!this.studentsCount.equals(((StudyGroup) o).studentsCount)) {
            return this.studentsCount.compareTo(((StudyGroup) o).studentsCount);
        }
        return this.id.compareTo(((StudyGroup) o).id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyGroup that = (StudyGroup) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(coordinates, that.coordinates) && Objects.equals(creationDate, that.creationDate) && Objects.equals(studentsCount, that.studentsCount) && formOfEducation == that.formOfEducation && semesterEnum == that.semesterEnum && Objects.equals(groupAdmin, that.groupAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, studentsCount, formOfEducation, semesterEnum, groupAdmin);
    }

    public class Builder {
        public Builder setId(Integer id) {
            StudyGroup.this.id = id;

            return this;
        }

        public Builder setName(String name) {
            StudyGroup.this.name = name;

            return this;
        }

        public Builder setCoordinates(Coordinates coordinates) {
            StudyGroup.this.coordinates = coordinates;

            return this;
        }

        public Builder setStudentsCount(Integer studentsCount) {
            StudyGroup.this.studentsCount = studentsCount;

            return this;
        }

        public Builder setFormOfEducation(FormOfEducation formOfEducation) {
            StudyGroup.this.formOfEducation = formOfEducation;

            return this;
        }

        public Builder setSemesterEnum(Semester semesterEnum) {
            StudyGroup.this.semesterEnum = semesterEnum;

            return this;
        }

        public Builder setGroupAdmin(Person groupAdmin) {
            StudyGroup.this.groupAdmin = groupAdmin;

            return this;
        }

        public StudyGroup build() {
            StudyGroup.this.creationDate = new Date();
            return StudyGroup.this;
        }
    }
}
