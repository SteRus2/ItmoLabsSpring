package us.obviously.itmo.prog.model;

public class Coordinates {
    private Long x; //Поле не может быть null
    private float y; //Значение поля должно быть больше -373
    public Coordinates(){}
    public Coordinates(Long x, float y) {
        this.x = x;
        this.y = y;
    }
}
