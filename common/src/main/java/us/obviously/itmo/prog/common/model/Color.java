package us.obviously.itmo.prog.common.model;

import java.io.Serializable;

public enum Color implements Serializable {
    RED("Красный", "color.red"),
    YELLOW("Жёлтый", "color.yellow"),
    ORANGE("Оранжевый", "color.orange"),
    WHITE("Белый", "color.white");

    public final String name;
    public final String key;

    Color(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public static Color[] getOptions() {
        return new Color[]{
                null,
                RED,
                YELLOW,
                ORANGE,
                WHITE
        };
    }
}
