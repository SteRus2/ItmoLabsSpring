package us.obviously.itmo.prog.gui.tools.fields;

import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

import java.util.function.Function;

public class AbstractComboBoxField<T> extends AbstractReqField<T, ComboBox<T>> {

    public AbstractComboBoxField(String label, T[] options, Function<T, String> function, T defaultValue) {
        super(label, new ComboBox<>());

        getControl().getItems().addAll(options);
        getControl().setValue(defaultValue);
        getControl().setConverter(new StringConverter<>() {
            @Override
            public String toString(T object) {
                if (object == null) return "";
                return function.apply(object);
            }

            @Override
            public T fromString(String string) {
                return null;
            }
        });
    }

    @Override
    public T getValue() {
        return getControl().getValue();
    }

    @Override
    public void setValue(T value) {
        getControl().setValue(value);
    }
}
