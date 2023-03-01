package us.obviously.itmo.prog.forms;

import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.manager.Management;
import us.obviously.itmo.prog.model.*;

import java.util.HashMap;

public class StudyGroupForm {

    StudyGroup value;
    Management manager;
    Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer studentsCount; //Значение поля должно быть больше 0, Поле может быть null
    private FormOfEducation formOfEducation; //Поле не может быть null
    private Semester semesterEnum; //Поле не может быть null
    private Person groupAdmin; //Поле не может быть null

    HashMap<String, FormOfEducation> formsOfEducation;
    HashMap<String, Semester> semesters;
    StudyGroup.Builder builder;
    public StudyGroupForm(Management manager, StudyGroup group) {
        this.manager = manager;
        this.value = group;
        this.builder = StudyGroup.newBuilder();
        this.formsOfEducation = new HashMap<>();

        this.formsOfEducation.put("1", FormOfEducation.FULL_TIME_EDUCATION);
        this.formsOfEducation.put("2", FormOfEducation.DISTANCE_EDUCATION);
        this.formsOfEducation.put("3", FormOfEducation.EVENING_CLASSES);

        this.semesters = new HashMap<>();

        this.semesters.put("2", Semester.SECOND);
        this.semesters.put("3", Semester.THIRD);
        this.semesters.put("5", Semester.FIFTH);
        this.semesters.put("7", Semester.SEVENTH);
        this.semesters.put("8", Semester.EIGHTH);
    }

    public StudyGroupForm(Management manager) {
        this(manager, new StudyGroup());
    }

    public void run(String key) {

        var scanner = this.manager.getScanner();

        while (true) {
            try {
                this.setId(key);
                break;
            } catch (final IncorrectValueException e) {
                System.out.println("Ошибка при вводе id: " + e.getMessage());
                System.out.print("Введите id: ");
                key = scanner.nextLine();
            }
        }

        while (true) {
            try {
                System.out.print("Введите name: ");
                var value = scanner.nextLine();
                this.setName(value);
                break;
            } catch (final IncorrectValueException e) {
                System.out.println("Ошибка при вводе name: " + e.getMessage());
            }
        }

        var personForm = new PersonForm();
        System.out.println("\n" + ConsoleColors.BLACK_BRIGHT +
                "Заполнение groupAdmin'а..." + ConsoleColors.RESET);

        while (true) {
            try {
                System.out.print("Введите name: ");
                var value = scanner.nextLine();
                personForm.setName(value);
                break;
            } catch (final IncorrectValueException e) {
                System.out.println("Ошибка при вводе name: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Введите birthday: ");
                var value = scanner.nextLine();
                personForm.setBirthday(value);
                break;
            } catch (final IncorrectValueException e) {
                System.out.println("Ошибка при вводе birthday: " + e.getMessage());
            }
        }

        Person person = personForm.build();
    }

    public void setId(Integer value) throws IncorrectValueException {
        if (value == null) throw new IncorrectValueException("Поле id не может быть null.");
        if (value <= 0) throw new IncorrectValueException("Поле id должно быть больше 0.");
        if (this.id != null && this.id.equals(value)) return;
        if (this.manager.isIdExists(value)) throw new IncorrectValueException("Поле id должно быть уникальным.");
        this.id = value;
    }

    public void setId(String value) throws IncorrectValueException {
        try {
            Integer id = Integer.parseInt(value);
            this.setId(id);
        } catch (NumberFormatException e) {
            throw new IncorrectValueException("Некорректно указан ключ");
        }
    }


    public void setName(String value) throws IncorrectValueException {
        if (value == null) throw new IncorrectValueException("Поле name не может быть null.");
        if (value.equals("")) throw new IncorrectValueException("Поле name не может быть пустым.");
        this.name = value;
    }

    public void setCoordinates(String value) throws IncorrectValueException {
        String[] coordinates = value.split(",");
        if (coordinates.length > 2) throw new IncorrectValueException("Поле coordinates не соответствует шаблону <x:Long> или <x:Long>, <y:float>.");
        try {
            Long x = Long.parseLong(coordinates[0]);
            Float y = null;
            if (coordinates.length == 2) {
                y = Float.parseFloat(coordinates[1]);
                if (y <= -373) {
                    throw new IncorrectValueException("Поле coordinates.y должно быть больше -373.");
                }
            }
            this.coordinates = new Coordinates(x, y);
        } catch (NumberFormatException e) {
            throw new IncorrectValueException("Поле coordinates не соответствует шаблону <x:Long> или <x:Long>, <y:float>.");
        }
    }

    public void setStudentsCount(Integer value) throws IncorrectValueException {
        if (value == null) return;
        if (value <= 0) throw new IncorrectValueException("Поле studentsCount должно быть больше 0.");
        this.studentsCount = value;
    }

    public void setFormOfEducation(String value) throws IncorrectValueException {
        if (value == null) throw new IncorrectValueException("Поле formOfEducation не может быть null.");
        FormOfEducation formOfEducation = this.formsOfEducation.get(value);
        if (formOfEducation == null) throw new IncorrectValueException("%s не является допустимым значением formOfEducation.".formatted(value));
        this.formOfEducation = formOfEducation;
    }

    public void setSemesterEnum(String value) throws IncorrectValueException {
        if (value == null) throw new IncorrectValueException("Поле semesterEnum не может быть null.");
        Semester semesterEnum = this.semesters.get(value);
        if (semesterEnum == null) throw new IncorrectValueException("%s не является допустимым значением semesterEnum.".formatted(value));
        this.semesterEnum = semesterEnum;
    }

    public StudyGroup build() {
        return this.builder.build();
    }
}
