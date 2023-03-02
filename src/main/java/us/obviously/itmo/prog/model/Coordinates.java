package us.obviously.itmo.prog.model;

public class Coordinates {
    private Long x; //Поле не может быть null
    private Float y; //Значение поля должно быть больше -373

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

        public Coordinates build() {
            return Coordinates.this;
        }
    }

}
