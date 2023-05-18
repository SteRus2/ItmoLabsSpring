package us.obviously.itmo.prog.client.fields;

import us.obviously.itmo.prog.client.console.ConsoleColors;
import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.manager.Management;

public class StringFormField extends FormField<String> {
    public StringFormField(Management manager, String key, Callback<String> callback, Boolean nil, String defaultInput, String autofill) {
        super(manager, key, callback, nil, defaultInput, defaultInput, autofill);
    }

    public StringFormField(Management manager, String key, Callback<String> callback) {
        super(manager, key, callback, false, null, null, null);
    }

    @Override
    void printSuccessMessage(String value) {
        if (value == null) {
            Messages.printStatement(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано null.".formatted(this.getKey()) + "~=");
        } else {
            Messages.printStatement(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано %s.".formatted(this.getKey(), value) + "~=");
        }
    }

    @Override
    public String convert(String value) {

        if (value.equals("")) return null;
        return value;
    }
}
