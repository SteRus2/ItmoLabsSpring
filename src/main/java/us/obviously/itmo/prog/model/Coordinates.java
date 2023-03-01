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
    
    public Coordinates() {}

    public Long getX() {
        return x;
    }

    public Float getY() {
        return y;
    }
}
