package us.obviously.itmo.prog.forms;

import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.manager.Management;

abstract public class FormField<T> {
    private final Management manager;
    private final Boolean nil = false;
    private final String key;
    private final String defaultInput;
    private final Callback<T> callback;
    private T value = null;

    public FormField(Management manager, String key, Callback<T> callback, String defaultInput) {
        this.manager = manager;
        this.key = key;
        this.callback = callback;
        this.defaultInput = defaultInput;
    }

    public final void run() {
        if (defaultInput != null) {
            if (this.checkValue(defaultInput)) {
                printSuccessMessage(this.value);
                return;
            }
            ;
        }

        this.printIntroMessage();

        while (true) {
            var res = this.recordValue();
            if (res) break;
        }
        printSuccessMessage(this.value);
    }

    public String getKey() {
        return key;
    }

    public Boolean getNil() {
        return nil;
    }

    public String getDefaultValue() {
        return defaultInput;
    }

    public Callback<T> getCallback() {
        return callback;
    }

    public boolean recordValue() {
        this.printLoopMessage();
        var line = this.manager.nextLine();
        return checkValue(line);
    }

    public boolean checkValue(String line) {
        try {
            this.value = this.convert(line);
            this.callback.callback(this.value);
        } catch (IncorrectValueException e) {
            Messages.printStatement(ConsoleColors.RED + "Ошибка: " + e.getMessage() + ConsoleColors.RESET);
            return false;
        }
        return true;
    }

    void printIntroMessage() {
    }

    void printLoopMessage() {
        Messages.print("Введите %s: ", this.key);
    }

    abstract void printSuccessMessage(T value);

    abstract public T convert(String value) throws IncorrectValueException;

    public interface Callback<T> {
        void callback(T value) throws IncorrectValueException;
    }
}
