package us.obviously.itmo.prog.gui.tools.fields;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.util.Collection;

public abstract class AbstractField<S, T extends Control> {
    private final String label;
    private final T control;
    private final ValidationSupport validationSupport;

    public AbstractField(String label, T control) {
        this.label = label;
        this.control = control;
        this.validationSupport = new ValidationSupport();
        control.focusedProperty().addListener((observable, oldFocused, newFocused) -> {
            if (oldFocused && !newFocused) { // on blur
                validate();
            }
        });
    }

    public Label createLabel() {
        return new Label(label);
    }

    public T getControl() {
        return control;
    }

    public ValidationSupport getSupport() {
        return validationSupport;
    }

    public void registerValidator(Validator<S> validator) {
        registerValidator(true, validator);
    }

    public void registerValidator(boolean required, Validator<S> validator) {
        validationSupport.registerValidator(control, required, validator);
    }

    public void addToGrid(GridPane grid, int rowIndex) {
        grid.add(this.createLabel(), 0, rowIndex);
        grid.add(this.getControl(), 1, rowIndex);
    }

    public Collection<ValidationMessage> validate() {
        return validationSupport.getValidationResult().getErrors();
    }
}
