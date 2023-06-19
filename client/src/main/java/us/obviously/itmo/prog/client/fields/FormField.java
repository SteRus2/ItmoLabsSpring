package us.obviously.itmo.prog.client.fields;

import us.obviously.itmo.prog.common.console.Messages;
import us.obviously.itmo.prog.common.server.exceptions.FormInterruptException;
import us.obviously.itmo.prog.common.server.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.client.forms.SelectChoice;
import us.obviously.itmo.prog.client.manager.Management;

import java.util.HashMap;
import java.util.Objects;

abstract public class FormField<T> {

    static public boolean exited = false;
    private final Management manager;
    private final Boolean nil;
    private final String key;
    private final T defaultValue;
    private final String autofill;
    private final Callback<T> callback;
    private final String defaultValueStr;
    private Values nullResult = null;
    private boolean skip = false;
    private Boolean silent = false;
    private T value = null;

    public FormField(Management manager, String key, Callback<T> callback, Boolean nil, T defaultValue, String defaultValueStr, String autofill) {
        this.manager = manager;
        this.key = key;
        this.callback = callback;
        if (nil == null) nil = false;
        this.nil = nil;
        this.defaultValue = defaultValue;
        if (defaultValueStr != null) {
            this.defaultValueStr = defaultValueStr;
        } else if (defaultValue != null) {
            this.defaultValueStr = defaultValue.toString();
        } else {
            this.defaultValueStr = null;
        }
        this.autofill = autofill;
    }

    public final void run() throws FormInterruptException {
        if (autofill != null) {
            if (this.checkValue(autofill)) {
                printSuccessMessageIfPossible(this.value);
                return;
            }
        }

        this.printIntroMessage();

        while (true) {
            var res = this.recordValue();
            if (res) break;
        }
        printSuccessMessageIfPossible(this.value);
    }

    public String getKey() {
        return key;
    }

    public Boolean getNil() {
        return nil;
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    public String getAutofill() {
        return autofill;
    }

    public Callback<T> getCallback() {
        return callback;
    }

    final public boolean recordValue() throws FormInterruptException {
        this.printLoopMessage();
        var line = this.manager.nextLine();
        line = line.replace("//", "/");
        if (line.equals("/exit")) {
            FormField.exited = true;
            throw new FormInterruptException("Прервано пользователем.");
        }
        return checkValue(line);
    }

    final public boolean checkValue(String line) throws FormInterruptException {
        this.skip = false;
        try {
            if (Objects.equals(line, "")) {
                this.value = this.handleNull(line);
                if (this.skip) return false;
            } else {
                this.value = this.convert(line);
            }
            this.callback.callback(this.value);
        } catch (IncorrectValueException e) {
            Messages.printStatement("~reОшибка: " + e.getMessage() + "~=");
            return false;
        }
        return true;
    }

    void printIntroMessage() {
    }

    void printLoopMessage() {
        if (!this.nil && this.defaultValue == null) {
            Messages.print("Введите %s: ", this.key);
            return;
        }
        if (!this.nil) {
            Messages.print("Введите %s (%s): ", this.key, this.defaultValueStr);
            return;
        }
        if (this.defaultValue == null) {
            Messages.print("Введите %s (NULL): ", this.key);
            return;
        }
        Messages.print("Введите %s (%s / NULL): ", this.key, this.defaultValueStr);
    }

    private T handleNull(String value) throws IncorrectValueException, FormInterruptException {
        if (!this.nil && this.defaultValue == null) {
            return this.convert(value);
        }
        if (!this.nil) {
            return this.defaultValue;
        }
        if (this.defaultValue == null) {
            return null;
        }

        var semesters = new HashMap<String, SelectChoice<Values>>();

        Callback<Values> setNullDefaultOption = (v) -> {
            if (v == null) throw new IncorrectValueException("Нужно вводить 1, 2 или 3.");
            this.nullResult = v;
        };

        semesters.put("1", new SelectChoice<>("Оставить " + this.defaultValueStr, Values.DEFAULT));
        semesters.put("2", new SelectChoice<>("Сделать NULL", Values.NULL));
        semesters.put("3", new SelectChoice<>("Ввести ручками", Values.CANCEL));
        // TODO: Это костыльно
        var formField = new DropdownSelectFormField<>(manager, "что с этим сделать", setNullDefaultOption, semesters);
        formField.mute();
        formField.run();
        if (nullResult == Values.NULL) return null;
        if (nullResult == Values.DEFAULT) return this.defaultValue;
        this.skip = true;
        return null;
    }

    public void mute() {
        this.silent = true;
    }

    public void unmute() {
        this.silent = false;
    }

    private void printSuccessMessageIfPossible(T value) {
        if (!this.silent && !FormField.exited) {
            this.printSuccessMessage(value);
        }
    }

    abstract void printSuccessMessage(T value);

    abstract public T convert(String value) throws IncorrectValueException;

    enum Values {
        DEFAULT,
        NULL,
        CANCEL
    }

    public interface Callback<T> {
        void callback(T value) throws IncorrectValueException, FormInterruptException;
    }
}
