package us.obviously.itmo.prog.forms;

import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.manager.Management;

public class IntegerFormField extends FormField<Integer> {
    public IntegerFormField(Management manager, String key, Callback<Integer> callback, String defaultInput) {
        super(manager, key, callback, defaultInput);
    }
    public IntegerFormField(Management manager, String key, Callback<Integer> callback) {
        super(manager, key, callback, null);
    }

    @Override
    void printSuccessMessage(Integer value) {
        if (value == null) {
            Messages.printStatement(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано null.".formatted(this.getKey()) + ConsoleColors.RESET);
        } else {
            Messages.printStatement(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано %s.".formatted(this.getKey(), value) + ConsoleColors.RESET);
        }
    }

    @Override
    public Integer convert(String value) throws IncorrectValueException {
        if (value.equals("")) return null;
        try {
            var maxLen = String.valueOf(Integer.MAX_VALUE).length() - 1;
            if (value.length() > maxLen) {
                throw new IncorrectValueException("Слишком большое число. Наши системы не поддерживают работу с целыми числами, большими 999,999,999.");
            }
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IncorrectValueException("Неверный формат целого числа.");
        }
    }
}
