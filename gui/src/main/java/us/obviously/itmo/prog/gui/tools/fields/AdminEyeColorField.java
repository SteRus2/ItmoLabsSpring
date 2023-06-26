package us.obviously.itmo.prog.gui.tools.fields;

import us.obviously.itmo.prog.common.model.Color;
import us.obviously.itmo.prog.gui.i18n.Internalization;

public class AdminEyeColorField extends AbstractComboBoxField<Color> {

    public AdminEyeColorField() {
        this(null);
    }

    public AdminEyeColorField(Color defaultValue) {
        super("admin.eyeColor", Color.getOptions(), value -> Internalization.getTranslation(value.key), defaultValue);
    }
}
