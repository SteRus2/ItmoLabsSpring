package us.obviously.itmo.prog.gui.tools.fields;

import javafx.scene.control.TextField;

public class AbstractTextField extends AbstractReqField<String, TextField> {
    public AbstractTextField(String label) {
        this(label, "");
    }

    public AbstractTextField(String label, String defaultValue) {
        super(label, new TextField(defaultValue));
    }

    @Override
    public String getValue() {
        return getControl().getText();
    }
}
