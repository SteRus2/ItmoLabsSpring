package us.obviously.itmo.prog.forms;

import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.manager.Management;

public class StringFormField extends FormField<String> {
    public StringFormField(Management manager, String key, Callback<String> callback, String defaultInput) {
        super(manager, key, callback, defaultInput);
    }

    public StringFormField(Management manager, String key, Callback<String> callback) {
        super(manager, key, callback, null);
    }

    @Override
    void printSuccessMessage(String value) {
        if (value == null) {
            Messages.printStatement(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано null.".formatted(this.getKey()) + ConsoleColors.RESET);
        } else {
            Messages.printStatement(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано %s.".formatted(this.getKey(), value) + ConsoleColors.RESET);
        }
    }

    @Override
    public String convert(String value) {

        if (value.equals("")) return null;
        return value;
    }
}
