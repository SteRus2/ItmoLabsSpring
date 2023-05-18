package us.obviously.itmo.prog.client.forms;

import us.obviously.itmo.prog.client.console.ConsoleColors;
import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.console.TablesPrinter;
import us.obviously.itmo.prog.client.fields.*;
import us.obviously.itmo.prog.client.exceptions.FormInterruptException;
import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.common.model.Color;
import us.obviously.itmo.prog.common.model.Country;
import us.obviously.itmo.prog.common.model.Person;
import us.obviously.itmo.prog.common.validation.PersonValidation;

import java.time.ZonedDateTime;
import java.util.HashMap;


/**
 * Форма Персоналии
 */
public class PersonForm extends Form<Person> {
    private final Person.Builder builder;
    /**
     * Элементы меню выбора цветов
     */
    HashMap<String, SelectChoice<Color>> colors;
    /**
     * Элементы меню выбора национальностей
     */
    HashMap<String, SelectChoice<Country>> nationalities;

    public PersonForm(Management manager) {
        super(manager);
        this.builder = Person.newBuilder();
        this.colors = new HashMap<>();

        this.colors.put("0", new SelectChoice<>("Не определено", null));
        this.colors.put("1", new SelectChoice<>(Color.ORANGE.name, Color.ORANGE));
        this.colors.put("2", new SelectChoice<>(Color.RED.name, Color.RED));
        this.colors.put("3", new SelectChoice<>(Color.WHITE.name, Color.WHITE));
        this.colors.put("4", new SelectChoice<>(Color.YELLOW.name, Color.YELLOW));


        this.nationalities = new HashMap<>();

        this.nationalities.put("0", new SelectChoice<>("Не определено", null));
        this.nationalities.put("1", new SelectChoice<>(Country.FRANCE.name, Country.FRANCE));
        this.nationalities.put("2", new SelectChoice<>(Country.INDIA.name, Country.INDIA));
        this.nationalities.put("3", new SelectChoice<>(Country.JAPAN.name, Country.JAPAN));
        this.nationalities.put("4", new SelectChoice<>(Country.THAILAND.name, Country.THAILAND));
        this.nationalities.put("5", new SelectChoice<>(Country.VATICAN.name, Country.VATICAN));
    }

    /**
     * Форма обновления Персоналии <b>person</b>
     *
     * @throws FormInterruptException Выбросит исключение, если пользователь введёт команду прерывания заполнения формы
     */
    public void update(Person person) throws FormInterruptException {
        new StringFormField(manager, "name", this::setName, false, person.getName(), null).run();

        FormField.Callback<CommonAnswer> updateCoordinates = (CommonAnswer answer) -> {
            if (answer == CommonAnswer.YES) {
                Messages.printStatement("\n" + ConsoleColors.BLACK_BRIGHT +
                        "Заполнение birthday'а...~=");
                var dateForm = new DateForm(manager);
                dateForm.create();
                try {
                    this.setBirthday(dateForm.build());
                } catch (IncorrectValueException e) {
                    Messages.printStatement(e.getMessage());
                }
            } else {
                this.setBirthday(person.getBirthday());
            }
        };

        TablesPrinter.printTableDelimiter();

        var fillCoordinatesQuestion = new YesNoSelectFormField(manager, "Изменить дату рождения?", updateCoordinates);
        fillCoordinatesQuestion.mute();
        fillCoordinatesQuestion.run();

        new DropdownSelectFormField<>(manager, "hairColor", this::setHairColor, this.colors).run();
        new DropdownSelectFormField<>(manager, "eyeColor", this::setEyeColor, this.colors).run();
        new DropdownSelectFormField<>(manager, "nationality", this::setNationality, this.nationalities).run();
    }


    /**
     * Форма создания новой Персоналии
     *
     * @throws FormInterruptException Выбросит исключение, если пользователь введёт команду прерывания заполнения формы
     */
    public void create() throws FormInterruptException {
        new StringFormField(manager, "name", this::setName).run();

        Messages.printStatement("\n" + ConsoleColors.BLACK_BRIGHT +
                "Заполнение birthday'а...~=");
        var dateForm = new DateForm(manager);
        dateForm.create();
        try {
            this.setBirthday(dateForm.build());
        } catch (IncorrectValueException e) {
            Messages.printStatement(e.getMessage());
        }

        new DropdownSelectFormField<>(manager, "hairColor", this::setHairColor, this.colors).run();
        new DropdownSelectFormField<>(manager, "eyeColor", this::setEyeColor, this.colors).run();
        new DropdownSelectFormField<>(manager, "nationality", this::setNationality, this.nationalities).run();
    }

    /**
     * Валидация имени и обновление билдера
     *
     * @throws IncorrectValueException Выбросит исключение, если поле невалидно
     */
    public void setName(String value) throws IncorrectValueException {
        if (value == null) throw new IncorrectValueException("Поле name не может быть null.");
        if (value.equals("")) throw new IncorrectValueException("Поле name не может быть пустым.");
        this.builder.setName(value);
    }


    /**
     * Валидация дня рождения и обновление билдера
     *
     * @throws IncorrectValueException Выбросит исключение, если поле невалидно
     */
    public void setBirthday(ZonedDateTime value) throws IncorrectValueException {
        PersonValidation.validateBirthday(value);
        this.builder.setBirthday(value);
    }


    /**
     * Валидация цвета глаз и обновление билдера
     *
     * @throws IncorrectValueException Выбросит исключение, если поле невалидно
     */
    public void setEyeColor(Color value) throws IncorrectValueException {
        PersonValidation.validateEyeColor(value);
        this.builder.setEyeColor(value);
    }

    /**
     * Валидация цвета волос и обновление билдера
     *
     * @throws IncorrectValueException Выбросит исключение, если поле невалидно
     */
    public void setHairColor(Color value) throws IncorrectValueException {
        PersonValidation.validateHairColor(value);
        this.builder.setEyeColor(value);
    }

    /**
     * Валидация национальности и обновление билдера
     *
     * @throws IncorrectValueException Выбросит исключение, если поле невалидно
     */
    public void setNationality(Country value) throws IncorrectValueException {
        PersonValidation.validateNationality(value);
        this.builder.setNationality(value);
    }

    /**
     * Валидация готовой формы и сборка новой Персоналии
     *
     * @return Готовая Персоналия
     * @throws IncorrectValueException Выбросит исключение, если поле невалидно
     */
    public Person build() throws IncorrectValueException {
        return this.builder.build();
    }
}
