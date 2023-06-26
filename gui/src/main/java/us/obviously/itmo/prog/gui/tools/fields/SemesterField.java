package us.obviously.itmo.prog.gui.tools.fields;

import us.obviously.itmo.prog.common.model.Semester;
import us.obviously.itmo.prog.gui.i18n.Internalization;

public class SemesterField extends AbstractComboBoxField<Semester> {

    public SemesterField() {
        this(Semester.SECOND);
    }

    public SemesterField(Semester defaultValue) {
        super("semester", Semester.getOptions(), value -> Internalization.getTranslation(value.key), defaultValue);
    }
}
