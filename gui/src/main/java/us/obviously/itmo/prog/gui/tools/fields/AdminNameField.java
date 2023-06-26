package us.obviously.itmo.prog.gui.tools.fields;

import org.controlsfx.validation.ValidationResult;

public class AdminNameField extends AbstractTextField {

    public AdminNameField() {
        this("");
    }

    public AdminNameField(String defaultValue) {
        super("admin.name");
        registerValidator((control, value) -> {
            if (value == null || value.equals("")) {
                return ValidationResult.fromError(control, "field.admin.name.errors.blank");
            }
            return ValidationResult.fromInfo(control, "ok");
        });
    }
}
