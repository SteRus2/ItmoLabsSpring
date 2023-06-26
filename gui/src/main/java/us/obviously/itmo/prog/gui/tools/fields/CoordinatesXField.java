package us.obviously.itmo.prog.gui.tools.fields;

import javafx.scene.control.Slider;
import org.controlsfx.validation.ValidationResult;

public class CoordinatesXField extends AbstractField<Long, Slider, Double> {

    public CoordinatesXField() {
        this(0);
    }

    public CoordinatesXField(int defaultValue) {
        super("coordinates.x", new Slider(-400, 400, defaultValue));

        getControl().setShowTickMarks(true);
        getControl().setShowTickLabels(true);
        getControl().setBlockIncrement(1.0);
        getControl().setMajorTickUnit(50.0);
        getControl().setMinorTickCount(4);
        getControl().setSnapToTicks(true);

        this.registerValidator(((control, value) -> {
            return ValidationResult.fromErrorIf(control, "field.coordinates.x.errors.null", value == null);
        }));
    }

    @Override
    public Long getValue() {
        return Math.round(getControl().getValue());
    }

    @Override
    public void setValue(Long value) {
        getControl().setValue(value);
    }
}
