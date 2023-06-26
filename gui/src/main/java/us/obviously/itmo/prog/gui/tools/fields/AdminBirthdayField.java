package us.obviously.itmo.prog.gui.tools.fields;

import javafx.scene.control.DatePicker;
import org.controlsfx.validation.ValidationResult;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class AdminBirthdayField extends AbstractField<ZonedDateTime, DatePicker, LocalDate> {

    public AdminBirthdayField() {
        this(null);
    }

    public AdminBirthdayField(LocalDate defaultValue) {
        super("admin.birthday", new DatePicker(defaultValue));

        this.registerValidator(((control, value) -> {
            if (value == null) return ValidationResult.fromError(control, "field.coordinates.y.errors.null");
            if (value.isAfter(LocalDate.now()))
                return ValidationResult.fromError(control, "field.admin.birthday.errors.afterNow");
            if (value.isBefore(LocalDate.of(1900, 1, 1)))
                return ValidationResult.fromError(control, "field.admin.birthday.errors.before");
            return ValidationResult.fromInfo(control, "fine");
        }));
    }

    @Override
    public ZonedDateTime getValue() {
        return getControl().getValue().atStartOfDay(ZoneId.systemDefault());
    }
}