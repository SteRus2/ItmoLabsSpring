package us.obviously.itmo.prog.gui.tools.fields;

import us.obviously.itmo.prog.common.model.Country;
import us.obviously.itmo.prog.gui.i18n.Internalization;

public class AdminNationalityField extends AbstractComboBoxField<Country> {

    public AdminNationalityField() {
        this(null);
    }

    public AdminNationalityField(Country defaultValue) {
        super("admin.nationality", Country.getOptions(), value -> Internalization.getTranslation(value.key), defaultValue);
    }
}
