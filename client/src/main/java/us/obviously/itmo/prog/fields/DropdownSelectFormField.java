package us.obviously.itmo.prog.fields;

import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.forms.SelectChoice;
import us.obviously.itmo.prog.manager.Management;

import java.util.HashMap;

public class DropdownSelectFormField<T> extends SelectFormField<T> {

    public DropdownSelectFormField(Management manager, String key, Callback<T> callback, HashMap<String, SelectChoice<T>> choices, Boolean nil, T defaultInput, String defaultInputStr, String autofill) {
        super(manager, key, callback, choices, nil, defaultInput, defaultInputStr, autofill);
    }

    public DropdownSelectFormField(Management manager, String key, Callback<T> callback, HashMap<String, SelectChoice<T>> choices) {
        super(manager, key, callback, choices);
    }

    @Override
    void printIntroMessage() {
        this.choices.forEach((choiceKey, choice) -> {
            Messages.print("%s - %s%n", choiceKey, choice.description());
        });
    }
}
