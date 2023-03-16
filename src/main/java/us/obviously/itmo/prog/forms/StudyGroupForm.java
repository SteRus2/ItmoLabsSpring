package us.obviously.itmo.prog.forms;

import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.console.TablesPrinter;
import us.obviously.itmo.prog.exceptions.FormInterruptException;
import us.obviously.itmo.prog.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.fields.*;
import us.obviously.itmo.prog.manager.Management;
import us.obviously.itmo.prog.model.*;
import us.obviously.itmo.prog.validation.StudyGroupValidation;

import java.util.HashMap;

public class StudyGroupForm extends Form<StudyGroup> {

    StudyGroup value;
    HashMap<String, SelectChoice<FormOfEducation>> formsOfEducation;
    HashMap<String, SelectChoice<Semester>> semesters;
    StudyGroup.Builder builder;

    public StudyGroupForm(Management manager, StudyGroup group) {
        super(manager);
        this.value = group;
        this.builder = StudyGroup.newBuilder();
        this.formsOfEducation = new HashMap<>();

        this.formsOfEducation.put("1", new SelectChoice<>(FormOfEducation.FULL_TIME_EDUCATION.name, FormOfEducation.FULL_TIME_EDUCATION));
        this.formsOfEducation.put("2", new SelectChoice<>(FormOfEducation.DISTANCE_EDUCATION.name, FormOfEducation.DISTANCE_EDUCATION));
        this.formsOfEducation.put("3", new SelectChoice<>(FormOfEducation.EVENING_CLASSES.name, FormOfEducation.EVENING_CLASSES));

        this.semesters = new HashMap<>();

        this.semesters.put("2", new SelectChoice<>(Semester.SECOND.name, Semester.SECOND));
        this.semesters.put("3", new SelectChoice<>(Semester.THIRD.name, Semester.THIRD));
        this.semesters.put("5", new SelectChoice<>(Semester.FIFTH.name, Semester.FIFTH));
        this.semesters.put("7", new SelectChoice<>(Semester.SEVENTH.name, Semester.SEVENTH));
        this.semesters.put("8", new SelectChoice<>(Semester.EIGHTH.name, Semester.EIGHTH));
    }

    public StudyGroupForm(Management manager) {
        this(manager, new StudyGroup());
    }

    public void update(StudyGroup group) throws FormInterruptException {
        new IntegerFormField(manager, "id", this::findId, false, group.getId(), null).run();
        new StringFormField(manager, "name", this::setName, false, group.getName(), null).run();
        new DropdownSelectFormField<>(manager, "semesterEnum", this::setSemesterEnum, this.semesters, false, group.getSemesterEnum(), group.getSemesterEnum().name(), null).run();
        new DropdownSelectFormField<>(manager, "formOfEducation", this::setFormOfEducation, this.formsOfEducation, false, group.getFormOfEducation(), group.getFormOfEducation().name(), null).run();
        new IntegerFormField(manager, "studentCount", this::setStudentsCount, true, group.getStudentsCount(), null).run();

        FormField.Callback<CommonAnswer> updateCoordinates = (CommonAnswer answer) -> {
            if (answer == CommonAnswer.YES) {
                var coordinatesForm = new CoordinatesForm(manager);
                Messages.printStatement("%n~0bkЗаполнение coordinates...~=");
                coordinatesForm.update(group.getCoordinates());
                Coordinates coordinates = coordinatesForm.build();
                this.setCoordinates(coordinates);
            } else {
                this.setCoordinates(group.getCoordinates());
            }
        };

        TablesPrinter.printTableDelimiter();

        var fillCoordinatesQuestion = new YesNoSelectFormField(manager, "Изменить координаты?", updateCoordinates);
        fillCoordinatesQuestion.mute();
        fillCoordinatesQuestion.run();


        FormField.Callback<CommonAnswer> updateGroupAdmin = (CommonAnswer answer) -> {
            if (answer == CommonAnswer.YES) {
                var personForm = new PersonForm(manager);
                Messages.printStatement("%n~0bkЗаполнение groupAdmin'а...~=");
                personForm.update(group.getGroupAdmin());
                Person person = personForm.build();
                this.setGroupAdmin(person);
            } else {
                this.setGroupAdmin(group.getGroupAdmin());
            }
        };

        TablesPrinter.printTableDelimiter();

        var fillGroupAdminQuestion = new YesNoSelectFormField(manager, "Изменить админа?", updateGroupAdmin);
        fillGroupAdminQuestion.mute();
        fillGroupAdminQuestion.run();

    }

    public void create(String key) throws IncorrectValueException, FormInterruptException {
        FormField.exited = false;
        Messages.printStatement("Для прерывания введите ~gr/exit~=");
        Messages.printStatement("~0bkЗаполнение стади группы...~=");
        new IntegerFormField(manager, "id", this::setId, false, null, key).run();
        new StringFormField(manager, "name", this::setName).run();
        new DropdownSelectFormField<>(manager, "semesterEnum", this::setSemesterEnum, this.semesters).run();
        new DropdownSelectFormField<>(manager, "formOfEducation", this::setFormOfEducation, this.formsOfEducation).run();
        new IntegerFormField(manager, "studentCount", this::setStudentsCount, true, null, null).run();

        var coordinatesForm = new CoordinatesForm(manager);
        Messages.printStatement("%n~0bkЗаполнение coordinates...~=");
        coordinatesForm.create();
        Coordinates coordinates = coordinatesForm.build();
        this.setCoordinates(coordinates);

        var personForm = new PersonForm(manager);
        Messages.printStatement("%n~0bkЗаполнение groupAdmin'а...~=");
        personForm.create();
        Person person = personForm.build();
        this.setGroupAdmin(person);

    }

    public void setGroupAdmin(Person value) {
        this.builder.setGroupAdmin(value);
    }


    public void findId(Integer value) throws IncorrectValueException {
        StudyGroupValidation.validateId(value);
        Integer id = this.value.getId();
        if (id != null && id.equals(value)) return;
        if (!this.manager.isIdExists(value)) throw new IncorrectValueException("Не удалось найти группу с таким id.");
        this.builder.setId(value);
    }

    public void setId(Integer value) throws IncorrectValueException {
        StudyGroupValidation.validateId(value);
        Integer id = this.value.getId();
        if (id != null && id.equals(value)) return;
        if (this.manager.isIdExists(value)) throw new IncorrectValueException("Поле id должно быть уникальным.");
        this.builder.setId(value);
    }

    public void setName(String value) throws IncorrectValueException {
        StudyGroupValidation.validateName(value);
        this.builder.setName(value);
    }

    public void setCoordinates(Coordinates coordinates) {
        this.builder.setCoordinates(coordinates);
    }

    public void setStudentsCount(Integer value) throws IncorrectValueException {
        StudyGroupValidation.validateStudentsCount(value);
        this.builder.setStudentsCount(value);
    }

    public void setFormOfEducation(FormOfEducation value) throws IncorrectValueException {
        StudyGroupValidation.validateFormOfEducation(value);
        this.builder.setFormOfEducation(value);
    }

    public void setSemesterEnum(Semester value) throws IncorrectValueException {
        StudyGroupValidation.validateSemesterEnum(value);
        this.builder.setSemesterEnum(value);
    }

    public StudyGroup build() throws IncorrectValueException {
        return this.builder.build();
    }
}
