package us.obviously.itmo.prog.fields;

import us.obviously.itmo.prog.exceptions.IncorrectValueException;

import java.util.Arrays;

public class StringField extends Field<String> {

    Boolean blank;
    String[] choices = null;

    public StringField(Boolean blank, Boolean nil, String defaultValue) {
        super(nil, defaultValue);
        this.blank = blank;
    }

    public StringField(Boolean blank, Boolean nil) {
        super(nil);
        this.blank = blank;
    }

    public StringField(Boolean blank) {
        super();
        this.blank = blank;
    }

    public StringField() {
        super();
        this.blank = false;
    }

    public StringField setChoices(String[] choices) {
        this.choices = choices;
        return this;
    }

    @Override
    public void check(String value) throws IncorrectValueException {
        super.check(value);
        this.checkBlank(value);
        this.checkChoices(value);
    }

    public void checkBlank(String value) throws IncorrectValueException {
        if (this.blank) return;
        if (!this.value.equals("")) return;
        throw new IncorrectValueException("Строка не может быть пустой.");
    }

    public void checkChoices(String value) throws IncorrectValueException {
        if (this.choices == null) return;
        if (Arrays.asList(this.choices).contains(value)) return;
        String values = Arrays.toString(this.choices);
        throw new IncorrectValueException("Строка должно принимать одно из возможных значений: %s.".formatted(values));
    }
}
