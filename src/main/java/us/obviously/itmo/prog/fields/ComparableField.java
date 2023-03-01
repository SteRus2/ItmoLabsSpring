package us.obviously.itmo.prog.fields;

import us.obviously.itmo.prog.exceptions.IncorrectValueException;

public class ComparableField<T> extends Field<Comparable<T>> {

    T minValue;
    T maxValue;

    public ComparableField(Boolean nil, Comparable<T> defaultValue) {
        super(nil, defaultValue);
    }

    public ComparableField(Boolean nil) {
        super(nil);
    }

    public ComparableField() {
        super();
    }

    public ComparableField<T> setMaxValue(T maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    public ComparableField<T> setMinValue(T minValue) {
        this.minValue = minValue;
        return this;
    }

    @Override
    public void check(Comparable<T> value) throws IncorrectValueException {
        super.check(value);
        this.checkMinValue(value);
        this.checkMaxValue(value);
    }

    public void checkMinValue(Comparable<T> value) throws IncorrectValueException {
        if (this.minValue == null) return;
        if (value == null) return;
        if (value.compareTo(this.minValue) >= 0) return;
        throw new IncorrectValueException("Значение поля должно быть не меньше %s".formatted(this.minValue));
    }

    public void checkMaxValue(Comparable<T> value) throws IncorrectValueException {
        if (this.maxValue == null) return;
        if (value == null) return;
        if (value.compareTo(this.maxValue) <= 0) return;
        throw new IncorrectValueException("Значение поля должно быть не больше %s".formatted(this.maxValue));
    }
}
