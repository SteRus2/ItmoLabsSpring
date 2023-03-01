package us.obviously.itmo.prog.forms;

import us.obviously.itmo.prog.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.model.Color;
import us.obviously.itmo.prog.model.Country;
import us.obviously.itmo.prog.model.Person;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;


public class PersonForm {
    private final Person.Builder builder;
    HashMap<String, Color> colors;
    HashMap<String, Country> nationalities;

    public PersonForm() {
        this.builder = Person.newBuilder();
        this.colors = new HashMap<>();

        this.colors.put("1", Color.ORANGE);
        this.colors.put("2", Color.RED);
        this.colors.put("3", Color.WHITE);
        this.colors.put("4", Color.YELLOW);


        this.nationalities = new HashMap<>();

        this.nationalities.put("1", Country.FRANCE);
        this.nationalities.put("2", Country.INDIA);
        this.nationalities.put("3", Country.JAPAN);
        this.nationalities.put("4", Country.THAILAND);
        this.nationalities.put("5", Country.VATICAN);
    }

    public void setName(String value) throws IncorrectValueException {
        if (value == null) throw new IncorrectValueException("Поле name не может быть null.");
        if (value.equals("")) throw new IncorrectValueException("Поле name не может быть пустым.");
        this.builder.setName(value);
    }

    public void setBirthday(String value) throws IncorrectValueException {
        try {
            var birthday = ZonedDateTime.parse(value);
            this.builder.setBirthday(birthday);
        } catch (DateTimeParseException e) {
            throw new IncorrectValueException("Невалидная дата. Воспользуйтесь шаблоном dd-mm-yyyy.");
        }
    }

    public void setEyeColor(String value) throws IncorrectValueException {
        if (value == null) return;
        Color color = this.colors.get(value);
        if (color == null)
            throw new IncorrectValueException("%s не является допустимым значением color.".formatted(value));
        this.builder.setEyeColor(color);
    }

    public void setHairColor(String value) throws IncorrectValueException {
        if (value == null) return;
        Color color = this.colors.get(value);
        if (color == null)
            throw new IncorrectValueException("%s не является допустимым значением color.".formatted(value));
        this.builder.setHairColor(color);
    }

    public void setNationality(String value) throws IncorrectValueException {
        if (value == null) return;
        Country nationality = this.nationalities.get(value);
        if (nationality == null)
            throw new IncorrectValueException("%s не является допустимым значением nationality.".formatted(value));
        this.builder.setNationality(nationality);
    }

    public Person build() {
        return this.builder.build();
    }
}
