package us.obviously.itmo.prog.forms;

import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.console.TablesPrinter;
import us.obviously.itmo.prog.exceptions.FormInterruptException;
import us.obviously.itmo.prog.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.fields.*;
import us.obviously.itmo.prog.manager.Management;
import us.obviously.itmo.prog.model.Color;
import us.obviously.itmo.prog.model.Country;
import us.obviously.itmo.prog.model.Person;
import us.obviously.itmo.prog.validation.PersonValidation;

import java.time.ZonedDateTime;
import java.util.HashMap;


public class PersonForm extends Form<Person> {
    private final Person.Builder builder;
    HashMap<String, SelectChoice<Color>> colors;
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

    public void setName(String value) throws IncorrectValueException {
        if (value == null) throw new IncorrectValueException("Поле name не может быть null.");
        if (value.equals("")) throw new IncorrectValueException("Поле name не может быть пустым.");
        this.builder.setName(value);
    }

    public void setBirthday(ZonedDateTime value) throws IncorrectValueException {
        PersonValidation.validateBirthday(value);
        this.builder.setBirthday(value);
    }

    public void setEyeColor(Color value) throws IncorrectValueException {
        PersonValidation.validateEyeColor(value);
        this.builder.setEyeColor(value);
    }

    public void setHairColor(Color value) throws IncorrectValueException {
        PersonValidation.validateHairColor(value);
        this.builder.setEyeColor(value);
    }

    public void setNationality(Country value) throws IncorrectValueException {
        PersonValidation.validateNationality(value);
        this.builder.setNationality(value);
    }

    public Person build() throws IncorrectValueException {
        return this.builder.build();
    }
}
