package us.obviously.itmo.prog.gui.tools.fields;

import us.obviously.itmo.prog.common.model.Color;
import us.obviously.itmo.prog.gui.i18n.Internalization;

public class AdminHairColorField extends AbstractComboBoxField<Color> {

    public AdminHairColorField() {
        this(null);
    }

    public AdminHairColorField(Color defaultValue) {
        super("admin.hairColor", Color.getOptions(), value -> Internalization.getTranslation(value.key), defaultValue);
    }
}
