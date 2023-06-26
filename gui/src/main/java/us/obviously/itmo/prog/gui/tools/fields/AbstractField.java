package us.obviously.itmo.prog.gui.tools.fields;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import us.obviously.itmo.prog.gui.i18n.Internalization;

import java.util.Collection;

public abstract class AbstractField<S, T extends Control, R> {
    private static final String prefix = "field.";
    private final String label;
    private final T control;
    private final ValidationSupport validationSupport;
    private final String key;
    private Label labelItem;

    public AbstractField(String label, T control) {
        this.label = label;
        this.key = prefix + label;
        this.control = control;
        this.validationSupport = new ValidationSupport();
        this.validationSupport.registerValidator(control, false, (c, s) -> ValidationResult.fromInfo(c, "fine"));
        control.focusedProperty().addListener((observable, oldFocused, newFocused) -> {
            if (oldFocused && !newFocused) { // on blur
                validate();
            }
        });
    }

    public Label createLabel() {
        if (labelItem == null) {
            labelItem = new Label(key);
        }
        translate();
        return labelItem;
    }

    public T getControl() {
        return control;
    }

    public ValidationSupport getSupport() {
        return validationSupport;
    }

    public void registerValidator(Validator<R> validator) {
        registerValidator(true, validator);
    }

    public void registerValidator(boolean required, Validator<R> validator) {
        validationSupport.registerValidator(control, required, validator);
    }

    public void addToGrid(GridPane grid, int rowIndex) {
        grid.add(this.createLabel(), 0, rowIndex);
        grid.add(this.getControl(), 1, rowIndex);
    }

    public Collection<ValidationMessage> validate() {
        return validationSupport.getValidationResult().getErrors();
    }

    public String getKey() {
        return key;
    }

    public void translate() {
        labelItem.setText(Internalization.getTranslation(key));
    }

    public abstract S getValue();
}
