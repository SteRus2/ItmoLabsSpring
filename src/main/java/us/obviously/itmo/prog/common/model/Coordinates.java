package us.obviously.itmo.prog.common.model;

import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.common.validation.CoordinatesValidation;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private Long x; //Поле не может быть null
    private Float y; //Значение поля должно быть больше -373

    /**
     * Конструктор, устанавливающий параметры координат
     *
     * @param x Координата x - Long
     * @param y Координата y - Float
     */
    public Coordinates(Long x, Float y) {
        this.x = x;
        this.y = y;
    }


    public Coordinates(Long x) {
        this.x = x;
        this.y = null;
    }

    public Coordinates() {
    }

    public static Coordinates.Builder newBuilder() {
        return new Coordinates().new Builder();
    }

    public Long getX() {
        return x;
    }

    public Float getY() {
        return y;
    }


    public class Builder {

        public Coordinates.Builder setX(Long x) {
            Coordinates.this.x = x;

            return this;
        }

        public Coordinates.Builder setY(float y) {
            Coordinates.this.y = y;

            return this;
        }

        public Coordinates build() throws IncorrectValueException {
            CoordinatesValidation.validate(Coordinates.this);
            return Coordinates.this;
        }
    }

}
