package us.obviously.itmo.prog.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import us.obviously.itmo.prog.server.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.validation.StudyGroupValidation;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Класс, объектами которого управляет коллекция
 */
public class StudyGroup implements Comparable<StudyGroup>, Serializable {
    private Integer id; // Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; // Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; // Поле не может быть null
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private java.util.Date creationDate; // Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer studentsCount; // Значение поля должно быть больше 0, Поле может быть null
    private FormOfEducation formOfEducation; // Поле не может быть null
    private Semester semesterEnum; // Поле не может быть null
    private Person groupAdmin; // Поле не может быть null
    private int ownerId;
    private String ownerUsername;

    public StudyGroup() {
    }

    public Person getPerson() {
        return groupAdmin;
    }


    public StudyGroup(Integer id, String name, Coordinates coordinates, Date creationDate, Integer studentsCount, FormOfEducation formOfEducation, Semester semesterEnum, Person groupAdmin) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }

    public StudyGroup(Integer id, String name, Coordinates coordinates, Date creationDate, Integer studentsCount, FormOfEducation formOfEducation, Semester semesterEnum, Person groupAdmin, int ownerId, String ownerUsername) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
        this.ownerId = ownerId;
        this.ownerUsername = ownerUsername;
    }

    public static StudyGroup.Builder newBuilder() {
        return new StudyGroup().new Builder();
    }

    /**
     * Возвращает id
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Возвращает название группы
     *
     * @return Название группы
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает координаты
     *
     * @return координаты
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Устанавливает дату создания
     *
     * @param creationDate дата создания группы
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Возвращает дату создания группы
     *
     * @return Дата создания группы
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Возвращает количество студентов
     *
     * @return Количество студентов
     */
    public Integer getStudentsCount() {
        return studentsCount;
    }

    /**
     * Возвращает форму обучения
     *
     * @return Форма обучения
     */
    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    /**
     * Возвращает семестр
     *
     * @return Семемтр
     */
    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    /**
     * Возвращает админа группы
     *
     * @return Админ группы
     */
    public Person getGroupAdmin() {
        return groupAdmin;
    }

    /**
     * Позволяет сравнивать группы, сначала по количеству студентов, потом по id
     *
     * @param o the object to be compared.
     * @return Число, говорящее о том, какая группа "больше"
     */
    @Override
    public int compareTo(StudyGroup o) {
        if (!this.name.equals(o.name)) {
            return this.name.compareToIgnoreCase(o.name);
        }
        if (!this.studentsCount.equals(o.studentsCount)) {
            return this.studentsCount.compareTo(o.studentsCount);
        }
        return this.id.compareTo(o.id);
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

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
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

        public StudyGroup build() throws IncorrectValueException {
            StudyGroup.this.creationDate = new Date();
            StudyGroupValidation.validate(StudyGroup.this);
            return StudyGroup.this;
        }
    }
}
