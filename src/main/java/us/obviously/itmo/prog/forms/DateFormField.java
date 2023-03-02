package us.obviously.itmo.prog.forms;

import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.manager.Management;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

public class DateFormField extends FormField<ZonedDateTime> {
    public DateFormField(Management manager, String key, Callback<ZonedDateTime> callback, String defaultInput) {
        super(manager, key, callback, defaultInput);
    }

    public DateFormField(Management manager, String key, Callback<ZonedDateTime> callback) {
        super(manager, key, callback, null);
    }

    @Override
    void printSuccessMessage(ZonedDateTime value) {
        if (value == null) {
            System.out.println(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано null.".formatted(this.getKey()) + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано %s.".formatted(this.getKey(), value) + ConsoleColors.RESET);
        }
    }

    @Override
    void printLoopMessage() {
        System.out.printf("Введите %s в формате yyyy-MM-dd+HH-mm : ", this.getKey());
    }

    @Override
    public ZonedDateTime convert(String value) throws IncorrectValueException {

        String[] parts = value.split("\\+");
        if (parts.length != 2) {
            throw new IncorrectValueException("Неверный формат.");
        }

        try {
            String datetime = parts[0] + "T00:00:00+" + parts[1];
            return ZonedDateTime.parse(datetime);
        } catch (DateTimeParseException ec) {
            throw new IncorrectValueException("Неверный формат: " + ec.getMessage());
        }
    }
}
