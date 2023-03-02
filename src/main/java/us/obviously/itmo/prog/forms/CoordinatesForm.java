package us.obviously.itmo.prog.forms;

import us.obviously.itmo.prog.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.manager.Management;
import us.obviously.itmo.prog.model.Color;
import us.obviously.itmo.prog.model.Country;
import us.obviously.itmo.prog.model.Coordinates;
import us.obviously.itmo.prog.validation.CoordinatesValidation;

import java.time.ZonedDateTime;
import java.util.HashMap;


public class CoordinatesForm extends Form {
    private final Coordinates.Builder builder;
    HashMap<String, SelectChoice<Color>> colors;
    HashMap<String, SelectChoice<Country>> nationalities;

    public CoordinatesForm(Management manager) {
        super(manager);
        this.builder = Coordinates.newBuilder();
        this.colors = new HashMap<>();
    }

    @Override
    public void run() {
        new LongFormField(manager, "x", this::setX).run();
        new FloatFormField(manager, "y", this::setY).run();
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

    public Coordinates build() {
        return this.builder.build();
    }
}
