package us.obviously.itmo.prog.gui.tools.fields;

import us.obviously.itmo.prog.common.model.FormOfEducation;
import us.obviously.itmo.prog.gui.i18n.Internalization;

public class FormOfEducationField extends AbstractComboBoxField<FormOfEducation> {

    public FormOfEducationField() {
        this(FormOfEducation.FULL_TIME_EDUCATION);
    }

    public FormOfEducationField(FormOfEducation defaultValue) {
        super("formOfEducation", FormOfEducation.getOptions(), value -> Internalization.getTranslation(value.key), defaultValue);
    }
}
