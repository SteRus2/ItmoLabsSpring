package us.obviously.itmo.prog.validation;

import us.obviously.itmo.prog.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.model.Color;
import us.obviously.itmo.prog.model.Country;
import us.obviously.itmo.prog.model.Person;

import java.time.ZonedDateTime;


public class PersonValidation {

    static public void validate(Person person) throws IncorrectValueException {
        validateName(person.getName());
        validateBirthday(person.getBirthday());
        validateEyeColor(person.getEyeColor());
        validateHairColor(person.getHairColor());
        validateNationality(person.getNationality());
    }

    static public void validateName(String value) throws IncorrectValueException {
        if (value == null) throw new IncorrectValueException("Поле name не может быть null.");
        if (value.equals("")) throw new IncorrectValueException("Поле name не может быть пустым.");
    }

    static public void validateBirthday(ZonedDateTime value) throws IncorrectValueException {
        if (value == null) throw new IncorrectValueException("Поле birthday не может быть null.");
    }

    static public void validateEyeColor(Color value) throws IncorrectValueException {
    }

    static public void validateHairColor(Color value) throws IncorrectValueException {
    }

    static public void validateNationality(Country value) throws IncorrectValueException {
    }
}
