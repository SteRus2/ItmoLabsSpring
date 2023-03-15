package us.obviously.itmo.prog.forms;

import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.manager.Management;

import java.util.HashMap;

public class SelectFormField<T> extends FormField<T> {
    private final HashMap<String, SelectChoice<T>> choices;
    private SelectChoice<T> selectedChoice = null;

    public SelectFormField(Management manager, String key, Callback<T> callback, HashMap<String, SelectChoice<T>> choices, String defaultInput) {
        super(manager, key, callback, defaultInput);
        this.choices = choices;
    }

    public SelectFormField(Management manager, String key, Callback<T> callback, HashMap<String, SelectChoice<T>> choices) {
        this(manager, key, callback, choices, null);
    }

    @Override
    void printIntroMessage() {
        this.choices.forEach((choiceKey, choice) -> {
            Messages.print("%s - %s%n", choiceKey, choice.description());
        });
    }

    @Override
    void printSuccessMessage(T value) {
        if (value == null) {
            Messages.printStatement(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано null.".formatted(this.getKey()) + ConsoleColors.RESET);
        } else {
            Messages.printStatement(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано %s.".formatted(this.getKey(), this.selectedChoice.description()) + ConsoleColors.RESET);
        }
    }

    @Override
    public T convert(String value) throws IncorrectValueException {
        if (value.equals("")) return null;
        boolean containsKey = this.choices.containsKey(value);
        if (!containsKey) throw new IncorrectValueException("Неизвестный ключ");
        this.selectedChoice = this.choices.get(value);

        return this.selectedChoice.value();
    }
}
