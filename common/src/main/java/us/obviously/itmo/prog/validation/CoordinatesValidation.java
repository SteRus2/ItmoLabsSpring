package us.obviously.itmo.prog.validation;

import us.obviously.itmo.prog.server.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.model.Coordinates;


/**
 * Класс валидации полей Координат
 */
public class CoordinatesValidation {

    /**
     * Валидация Координат, то есть проверка, что каждое её поле валидно.
     *
     * @param coordinates Координаты
     * @throws IncorrectValueException Выбросит исключение, если Координаты не прошли валидацию
     */
    static public void validate(Coordinates coordinates) throws IncorrectValueException {
        validateX(coordinates.getX());
        validateY(coordinates.getY());
    }

    /**
     * Проверка валидности координаты X для Координат
     *
     * @param value Проверяемое значение координаты X
     * @throws IncorrectValueException Выбросит исключение, если значение невалидно
     */
    static public void validateX(Long value) throws IncorrectValueException {
        if (value == null) throw new IncorrectValueException("Поле coordinates.x не может быть null.");
    }

    /**
     * Проверка валидности координаты Y для Координат
     *
     * @param value Проверяемое значение координаты Y
     * @throws IncorrectValueException Выбросит исключение, если значение невалидно
     */
    static public void validateY(Float value) throws IncorrectValueException {
        if (value == null) return;
        if (value <= -373) throw new IncorrectValueException("Поле coordinates.y должно быть больше -373.");
    }
}
