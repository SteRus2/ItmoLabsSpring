package us.obviously.itmo.prog.gui.tools.fields;

import javafx.scene.control.TextField;
import org.controlsfx.validation.ValidationResult;

public class GroupNameField extends AbstractTextField {

    public GroupNameField() {
        this("");
    }

    public GroupNameField(String defaultValue) {
        super("groupName");
        registerValidator((control, value) -> {
            if (value == null || value.equals("")) {
                return ValidationResult.fromError(control, "field.groupName.errors.blank");
            }
            return ValidationResult.fromInfo(control, "ok");
        });
    }
}