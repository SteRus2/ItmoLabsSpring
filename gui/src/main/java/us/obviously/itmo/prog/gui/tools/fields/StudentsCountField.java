package us.obviously.itmo.prog.gui.tools.fields;

import javafx.scene.control.Slider;
import org.controlsfx.validation.ValidationResult;

public class StudentsCountField extends AbstractField<Integer, Slider, Float> {

    public StudentsCountField() {
        this(1);
    }

    public StudentsCountField(int defaultValue) {
        super("studentsCount", new Slider(1, 40, defaultValue));

        getControl().setShowTickMarks(true);
        getControl().setShowTickLabels(true);
        getControl().setBlockIncrement(1.0);
        getControl().setMajorTickUnit(10.0);
        getControl().setMinorTickCount(5);
        getControl().setSnapToTicks(true);

        this.registerValidator(((control, value) -> {
            if (value == null) return ValidationResult.fromInfo(control, "fine");
            return ValidationResult.fromErrorIf(control, "field.studentsCount.errors.min", value < 1.0);
        }));
    }


    @Override
    public Integer getValue() {
        return Math.toIntExact(Math.round(getControl().getValue()));
    }
}
