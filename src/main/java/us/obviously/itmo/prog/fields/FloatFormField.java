package us.obviously.itmo.prog.fields;

import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.manager.Management;

public class FloatFormField extends FormField<Float> {
    public FloatFormField(Management manager, String key, Callback<Float> callback, Boolean nil, Float defaultInput, String autofill) {
        super(manager, key, callback, nil, defaultInput, defaultInput.toString(), autofill);
    }
    public FloatFormField(Management manager, String key, Callback<Float> callback, Boolean nil) {
        super(manager, key, callback, nil, null, null, null);
    }

    public FloatFormField(Management manager, String key, Callback<Float> callback) {
        super(manager, key, callback, false, null, null, null);
    }

    @Override
    void printSuccessMessage(Float value) {
        if (value == null) {
            Messages.printStatement(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано null.".formatted(this.getKey()) + "~=");
        } else {
            Messages.printStatement(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано %s.".formatted(this.getKey(), value) + "~=");
        }
    }

    @Override
    public Float convert(String value) throws IncorrectValueException {
        if (value.equals("")) return null;
        try {
            var maxLen = String.valueOf(Float.MAX_VALUE).length() - 1;
            if (value.length() > maxLen) {
                throw new IncorrectValueException("Слишком большое число. Наши системы не поддерживают работу с целыми числами, по модулю большими 999,999,999.");
            }
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            throw new IncorrectValueException("Неверный формат целого числа.");
        }
    }
}
