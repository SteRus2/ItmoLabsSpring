package us.obviously.itmo.prog.client.fields;

import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.forms.SelectChoice;
import us.obviously.itmo.prog.client.manager.Management;

import java.util.HashMap;

public class LinearSelectFormField<T> extends SelectFormField<T> {


    public LinearSelectFormField(Management manager, String key, Callback<T> callback, HashMap<String, SelectChoice<T>> choices, Boolean nil, T defaultInput, String defaultInputStr, String autofill) {
        super(manager, key, callback, choices, nil, defaultInput, defaultInputStr, autofill);
    }

    public LinearSelectFormField(Management manager, String key, Callback<T> callback, HashMap<String, SelectChoice<T>> choices) {
        super(manager, key, callback, choices);
    }

    void printLoopMessage() {
        var variants = String.join(" / ", this.choices.keySet());
        Messages.print("Введите %s ~0bk( %s )~=: ", this.getKey(), variants);
    }

}
