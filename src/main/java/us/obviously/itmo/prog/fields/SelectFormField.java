package us.obviously.itmo.prog.fields;

import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.forms.SelectChoice;
import us.obviously.itmo.prog.manager.Management;

import java.util.HashMap;

abstract public class SelectFormField<T> extends FormField<T> {
    protected final HashMap<String, SelectChoice<T>> choices;
    private SelectChoice<T> selectedChoice = null;

    public SelectFormField(Management manager, String key, Callback<T> callback, HashMap<String, SelectChoice<T>> choices, Boolean nil, T defaultInput, String defaultInputStr, String autofill) {
        super(manager, key, callback, nil, defaultInput, defaultInputStr, autofill);
        this.choices = choices;
        if (defaultInput != null) {
            choices.forEach((choiceKey, choice) -> {
                if (choice.value() == defaultInput) {
                    this.selectedChoice = choice;
                }
            });
        }
    }

    public SelectFormField(Management manager, String key, Callback<T> callback, HashMap<String, SelectChoice<T>> choices) {
        this(manager, key, callback, choices, false, null, null, null);
    }

    @Override
    void printSuccessMessage(T value) {
        if (value == null) {
            Messages.printStatement(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано null.".formatted(this.getKey()) + "~=");
        } else {
            Messages.printStatement(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано %s.".formatted(this.getKey(), this.selectedChoice.description()) + "~=");
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
