package us.obviously.itmo.prog.client.fields;

import us.obviously.itmo.prog.common.console.ConsoleColors;
import us.obviously.itmo.prog.common.console.Messages;
import us.obviously.itmo.prog.common.server.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.client.manager.Management;

public class IntegerFormField extends FormField<Integer> {
    public IntegerFormField(Management manager, String key, Callback<Integer> callback, Boolean nil, Integer defaultInput, String autofill) {
        super(manager, key, callback, nil, defaultInput, null, autofill);
    }

    public IntegerFormField(Management manager, String key, Callback<Integer> callback) {
        super(manager, key, callback, false, null, null, null);
    }

    @Override
    void printSuccessMessage(Integer value) {
        if (value == null) {
            Messages.printStatement(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано null.".formatted(this.getKey()) + "~=");
        } else {
            Messages.printStatement(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано %s.".formatted(this.getKey(), value) + "~=");
        }
    }

    @Override
    public Integer convert(String value) throws IncorrectValueException {
        if (value.equals("")) return null;
        try {
            var maxLen = String.valueOf(Integer.MAX_VALUE).length() - 1;
            if (value.length() > maxLen) {
                throw new IncorrectValueException("Слишком большое число. Наши системы не поддерживают работу с целыми числами, по модулю большими 999,999,999.");
            }
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IncorrectValueException("Неверный формат целого числа.");
        }
    }
}
