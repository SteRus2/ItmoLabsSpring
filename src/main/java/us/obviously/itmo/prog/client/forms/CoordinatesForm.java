package us.obviously.itmo.prog.client.forms;

import us.obviously.itmo.prog.client.exceptions.FormInterruptException;
import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.client.fields.FloatFormField;
import us.obviously.itmo.prog.client.fields.LongFormField;
import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.common.model.Color;
import us.obviously.itmo.prog.common.model.Coordinates;

import java.util.HashMap;


/**
 * Форма Координат
 */
public class CoordinatesForm extends Form<Coordinates> {
    private final Coordinates.Builder builder;
    final HashMap<String, SelectChoice<Color>> colors;

    public CoordinatesForm(Management manager) {
        super(manager);
        this.builder = Coordinates.newBuilder();
        this.colors = new HashMap<>();
    }

    public void update(Coordinates coordinates) throws FormInterruptException {
        new LongFormField(manager, "x", this::setX, false, coordinates.getX(), null).run();
        new FloatFormField(manager, "y", this::setY, true, coordinates.getY(), null).run();
    }

    public void create() throws FormInterruptException {
        new LongFormField(manager, "x", this::setX).run();
        new FloatFormField(manager, "y", this::setY, true).run();
    }

    public void setX(Long value) throws IncorrectValueException {
        if (value == null) throw new IncorrectValueException("Поле name не может быть null.");
        this.builder.setX(value);
    }

    public void setY(Float value) throws IncorrectValueException {
        if (value == null) return;
        if (value <= -373) throw new IncorrectValueException("Поле coordinates y должно быть больше -373.");
        this.builder.setY(value);
    }

    /**
     * Валидация готовой формы и сборка новых Координат
     *
     * @return Готовые Координаты
     * @throws IncorrectValueException Выбросит исключение, если поле невалидно
     */
    public Coordinates build() throws IncorrectValueException {
        return this.builder.build();
    }
}
