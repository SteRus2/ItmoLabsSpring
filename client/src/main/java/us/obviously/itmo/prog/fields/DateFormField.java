package us.obviously.itmo.prog.fields;

import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.server.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.manager.Management;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

public class DateFormField<T extends ZonedDateTime> extends FormField<T> {
    public DateFormField(Management manager, String key, Callback<T> callback, Boolean nil, T defaultInput, String autofill) {
        super(manager, key, callback, nil, defaultInput, defaultInput.toString(), autofill);
    }

    public DateFormField(Management manager, String key, Callback<T> callback) {
        super(manager, key, callback, false, null, null, null);
    }

    @Override
    void printSuccessMessage(T value) {
        if (value == null) {
            Messages.printStatement(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано null.".formatted(this.getKey()) + "~=");
        } else {
            Messages.printStatement(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано %s.".formatted(this.getKey(), value) + "~=");
        }
    }

    @Override
    void printLoopMessage() {
        Messages.print("Введите %s в формате yyyy-MM-dd+HH:mm : ", this.getKey());
    }

    @Override
    public T convert(String value) throws IncorrectValueException {
        if (value.equals("")) return null;

        String[] parts = value.split("\\+");
        if (parts.length != 2) {
            throw new IncorrectValueException("Неверный формат.");
        }

        try {
            String datetime = parts[0] + "T00:00:00+" + parts[1];
            return (T) T.parse(datetime);
        } catch (DateTimeParseException ec) {
            throw new IncorrectValueException("Неверный формат: " + ec.getMessage());
        }
    }
}
