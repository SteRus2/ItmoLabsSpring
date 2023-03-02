package us.obviously.itmo.prog.model;

public enum Color {
    RED("Красный"),
    YELLOW("Жёлтый"),
    ORANGE("Оранжевый"),
    WHITE("Белый");

    public final String name;

    Color(String name) {
        this.name = name;
    }
}
