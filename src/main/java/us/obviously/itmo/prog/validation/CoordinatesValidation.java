package us.obviously.itmo.prog.validation;

import us.obviously.itmo.prog.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.model.Coordinates;


public class CoordinatesValidation {

    static public void validate(Coordinates coordinates) throws IncorrectValueException {
        validateX(coordinates.getX());
        validateY(coordinates.getY());
    }

    static public void validateX(Long value) throws IncorrectValueException {
        if (value == null) throw new IncorrectValueException("Поле coordinates.x не может быть null.");
    }

    static public void validateY(Float value) throws IncorrectValueException {
        if (value == null) return;
        if (value <= -373) throw new IncorrectValueException("Поле coordinates.y должно быть больше -373.");
    }
}
