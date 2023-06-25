package us.obviously.itmo.prog.gui.tools.fields;

import javafx.scene.control.TextField;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;

public class GroupNameField extends AbstractField<String, TextField> {

    public GroupNameField() {
        super("Name", new TextField());
        this.registerValidator(((control, s) -> {
            return ValidationResult.fromMessageIf(control, s, Severity.ERROR, true);
        }));
    }
}
