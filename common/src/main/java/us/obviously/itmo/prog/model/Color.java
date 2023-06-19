package us.obviously.itmo.prog.model;

import java.io.Serializable;

public enum Color implements Serializable {
    RED("Красный"),
    YELLOW("Жёлтый"),
    ORANGE("Оранжевый"),
    WHITE("Белый");

    public final String name;

    Color(String name) {
        this.name = name;
    }
}
