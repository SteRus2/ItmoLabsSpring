package us.obviously.itmo.prog.gui.tools.fields;

import javafx.scene.control.Slider;
import org.controlsfx.validation.ValidationResult;

public class CoordinatesYField extends AbstractField<Float, Slider, Double> {

    public CoordinatesYField() {
        this(0.0);
    }

    public CoordinatesYField(double defaultValue) {
        super("coordinates.y", new Slider(-373.0, 500.0, defaultValue));

        getControl().setShowTickMarks(true);
        getControl().setShowTickLabels(true);
        getControl().setBlockIncrement(1.0);
        getControl().setMajorTickUnit(50.0);
        getControl().setMinorTickCount(4);
        getControl().setSnapToTicks(true);

        this.registerValidator(((control, value) -> {
            if (value == null)
                return ValidationResult.fromError(control, "field.coordinates.y.errors.null");
            if (value <= -373.0)
                return ValidationResult.fromError(control, "field.coordinates.y.errors.minValue");
            return ValidationResult.fromInfo(control, "fine");
        }));
    }

    @Override
    public Float getValue() {
        return (float) getControl().getValue();
    }

    @Override
    public void setValue(Float value) {
        getControl().setValue(value);
    }
}
