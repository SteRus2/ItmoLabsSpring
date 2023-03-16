package us.obviously.itmo.prog.fields;

import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.forms.SelectChoice;
import us.obviously.itmo.prog.manager.Management;

import java.util.HashMap;


public class YesNoSelectFormField extends LinearSelectFormField<CommonAnswer> {

    public YesNoSelectFormField(Management manager, String question, Callback<CommonAnswer> callback, CommonAnswer defaultInput) {
        super(manager, question, callback, new HashMap<>(), null, defaultInput, defaultInput != null ? defaultInput.getWord() : null, null);
        choices.put(CommonAnswer.YES.getWord(), new SelectChoice <>(CommonAnswer.YES.getWord(), CommonAnswer.YES));
        choices.put(CommonAnswer.NO.getWord(), new SelectChoice<>(CommonAnswer.NO.getWord(), CommonAnswer.NO));
    }

    public YesNoSelectFormField(Management manager, String key, Callback<CommonAnswer> callback) {
        this(manager, key, callback, null);
    }

    void printLoopMessage() {
        var variants = String.join("/", this.choices.keySet());
        Messages.print("%s ~0bk(%s)~=: ", this.getKey(), variants);
    }
}
