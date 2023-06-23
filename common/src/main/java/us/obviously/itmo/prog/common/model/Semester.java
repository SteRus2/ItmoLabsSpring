package us.obviously.itmo.prog.common.model;

import java.io.Serializable;

public enum Semester implements Serializable {
    SECOND("Второй", "second"),
    THIRD("Третий", "third"),
    FIFTH("Пятый", "fifth"),
    SEVENTH("Седьмой", "seventh"),
    EIGHTH("Восьмой", "eighth");

    public final String name;
    public final String key;

    Semester(String name, String key) {
        this.name = name;
        this.key = key;
    }
}
