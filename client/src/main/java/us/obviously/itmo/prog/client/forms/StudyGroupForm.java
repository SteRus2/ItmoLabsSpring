package us.obviously.itmo.prog.client.forms;

import us.obviously.itmo.prog.common.console.Messages;
import us.obviously.itmo.prog.common.console.TablesPrinter;
import us.obviously.itmo.prog.common.server.exceptions.FormInterruptException;
import us.obviously.itmo.prog.common.server.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.client.fields.*;
import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.common.server.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.server.exceptions.ServerErrorException;
import us.obviously.itmo.prog.common.model.*;
import us.obviously.itmo.prog.common.validation.StudyGroupValidation;

import java.util.HashMap;

/**
 * Форма Учебной Группы
 */
public class StudyGroupForm extends Form<StudyGroup> {

    final StudyGroup value;
    final HashMap<String, SelectChoice<FormOfEducation>> formsOfEducation;
    final HashMap<String, SelectChoice<Semester>> semesters;
    final StudyGroup.Builder builder;

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

    /**
     * Форма обновления Учебный Группы
     *
     * @throws FormInterruptException Выбросит исключение, если пользователь введёт команду прерывания заполнения формы
     */
    public void update(StudyGroup group) throws FormInterruptException {
        //new IntegerFormField(manager, "id", this::findId, false, group.getId(), null).run();
        this.builder.setId(group.getId());
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

    /**
     * Форма создания новой Учебной Группы
     *
     * @throws FormInterruptException Выбросит исключение, если пользователь введёт команду прерывания заполнения формы
     */
    public void create(String key) throws IncorrectValueException, FormInterruptException {
        FormField.exited = false;
        Messages.printStatement("Для прерывания введите ~gr/exit~=");
        Messages.printStatement("~0bkЗаполнение стади группы...~=");
        //new IntegerFormField(manager, "id", this::setId, false, null, key).run();
        setId(1);
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

    /**
     * Валидация администратора и обновление билдера
     */
    public void setGroupAdmin(Person value) {
        this.builder.setGroupAdmin(value);
    }

    /**
     * Проверка существует ли группы для обновления
     *
     * @throws IncorrectValueException Выбросит исключение, если поле невалидно
     */
    public void findId(Integer value) throws IncorrectValueException, BadRequestException, ServerErrorException {
        StudyGroupValidation.validateId(value);
        Integer id = this.value.getId();
        if (id != null && id.equals(value)) return;
        if (!this.manager.isIdExists(value)) throw new IncorrectValueException("Не удалось найти группу с таким id.");
        this.builder.setId(value);
    }

    /**
     * Валидация id и обновление билдера
     *
     * @throws IncorrectValueException Выбросит исключение, если поле невалидно
     */
    public void setId(Integer value) {
        //StudyGroupValidation.validateId(value);
        Integer id = this.value.getId();
        if (id != null && id.equals(value)) return;
        //if (this.manager.isIdExists(value)) throw new IncorrectValueException("Поле id должно быть уникальным.");
        this.builder.setId(value);
    }

    /**
     * Валидация имени и обновление билдера
     *
     * @throws IncorrectValueException Выбросит исключение, если поле невалидно
     */
    public void setName(String value) throws IncorrectValueException {
        StudyGroupValidation.validateName(value);
        this.builder.setName(value);
    }

    /**
     * Валидация координат и обновление билдера
     */
    public void setCoordinates(Coordinates coordinates) {
        this.builder.setCoordinates(coordinates);
    }

    /**
     * Валидация количества студентов и обновление билдера
     *
     * @throws IncorrectValueException Выбросит исключение, если поле невалидно
     */
    public void setStudentsCount(Integer value) throws IncorrectValueException {
        StudyGroupValidation.validateStudentsCount(value);
        this.builder.setStudentsCount(value);
    }

    /**
     * Валидация формы обучение и обновление билдера
     *
     * @throws IncorrectValueException Выбросит исключение, если поле невалидно
     */
    public void setFormOfEducation(FormOfEducation value) throws IncorrectValueException {
        StudyGroupValidation.validateFormOfEducation(value);
        this.builder.setFormOfEducation(value);
    }

    /**
     * Валидация семестра и обновление билдера
     *
     * @throws IncorrectValueException Выбросит исключение, если поле невалидно
     */
    public void setSemesterEnum(Semester value) throws IncorrectValueException {
        StudyGroupValidation.validateSemesterEnum(value);
        this.builder.setSemesterEnum(value);
    }

    /**
     * Валидация готовой формы и сборка новой Учебной Группы
     *
     * @return Готовая Группа
     * @throws IncorrectValueException Выбросит исключение, если поле невалидно
     */
    public StudyGroup build() throws IncorrectValueException {
        return this.builder.build();
    }
}
