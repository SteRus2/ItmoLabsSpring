package us.obviously.itmo.prog.gui.tools.fields;

import javafx.scene.control.CheckBox;

public class ReplaceIfGreaterField extends AbstractField<Boolean, CheckBox, Boolean> {

    public ReplaceIfGreaterField() {
        this(false);
    }

    public ReplaceIfGreaterField(boolean defaultValue) {
        super("replaceIfGreater", new CheckBox());
        getControl().setSelected(defaultValue);
    }


    @Override
    public Boolean getValue() {
        return this.getControl().isSelected();
    }

    @Override
    public void setValue(Boolean value) {
        getControl().setSelected(value);
    }
}
