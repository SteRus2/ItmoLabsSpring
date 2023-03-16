package us.obviously.itmo.prog.forms;

import us.obviously.itmo.prog.exceptions.FormInterruptException;
import us.obviously.itmo.prog.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.fields.FloatFormField;
import us.obviously.itmo.prog.fields.LongFormField;
import us.obviously.itmo.prog.manager.Management;
import us.obviously.itmo.prog.model.Color;
import us.obviously.itmo.prog.model.Coordinates;

import java.util.HashMap;


public class CoordinatesForm extends Form {
    private final Coordinates.Builder builder;
    HashMap<String, SelectChoice<Color>> colors;

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
        new FloatFormField(manager, "y", this::setY, true, null, null).run();
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

    public Coordinates build() throws IncorrectValueException {
        return this.builder.build();
    }
}
