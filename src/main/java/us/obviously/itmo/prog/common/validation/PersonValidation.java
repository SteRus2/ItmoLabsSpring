package us.obviously.itmo.prog.common.validation;

import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.common.model.Color;
import us.obviously.itmo.prog.common.model.Country;
import us.obviously.itmo.prog.common.model.Person;

import java.time.ZonedDateTime;


/**
 * Класс валидации полей Персоналии
 */
public class PersonValidation {

    /**
     * Валидация Персоналии, то есть проверка, что каждое её поле валидно.
     *
     * @param person Персоналия
     * @throws IncorrectValueException Выбросит исключение, если Персоналия не прошла валидацию
     */
    static public void validate(Person person) throws IncorrectValueException {
        validateName(person.getName());
        validateBirthday(person.getBirthday());
        validateEyeColor(person.getEyeColor());
        validateHairColor(person.getHairColor());
        validateNationality(person.getNationality());
    }

    /**
     * Проверка валидности имени для Персоналии
     *
     * @param value Проверяемое значение валидности имени
     * @throws IncorrectValueException Выбросит исключение, если значение невалидно
     */
    static public void validateName(String value) throws IncorrectValueException {
        if (value == null) throw new IncorrectValueException("Поле name не может быть null.");
        if (value.equals("")) throw new IncorrectValueException("Поле name не может быть пустым.");
    }

    /**
     * Проверка валидности даты рождения для Персоналии
     *
     * @param value Проверяемое значение даты рождения
     * @throws IncorrectValueException Выбросит исключение, если значение невалидно
     */
    static public void validateBirthday(ZonedDateTime value) throws IncorrectValueException {
        if (value == null) throw new IncorrectValueException("Поле birthday не может быть null.");
    }

    /**
     * Проверка валидности цвета глаз для Персоналии
     *
     * @param value Проверяемое значение цвета глаз
     */
    static public void validateEyeColor(Color value) {
    }

    /**
     * Проверка валидности цвета волос для Персоналии
     *
     * @param value Проверяемое значение цвета волос
     */
    static public void validateHairColor(Color value) {
    }

    /**
     * Проверка валидности национальности для Персоналии
     *
     * @param value Проверяемое значение национальности
     */
    static public void validateNationality(Country value) {
    }
}
