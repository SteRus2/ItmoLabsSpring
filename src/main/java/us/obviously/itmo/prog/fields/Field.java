package us.obviously.itmo.prog.fields;

import us.obviously.itmo.prog.exceptions.IncorrectValueException;

public class Field<T> {

    public final Boolean nil;
    T value;

    public Field(Boolean nil, T defaultValue) {
        this.nil = nil;
        value = defaultValue;
    }

    public Field(Boolean nil) {
        this.nil = nil;
        value = null;
    }

    public Field() {
        this.nil = false;
        value = null;
    }

    public void check(T value) throws IncorrectValueException {
        this.checkNil(value);
    }

    public void checkNil(T value) throws IncorrectValueException {
        if (!this.nil) return;
        if (this.value != null) return;
        throw new IncorrectValueException("Поле не может быть null.");
    }
}
