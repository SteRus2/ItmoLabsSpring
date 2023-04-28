package us.obviously.itmo.prog.common.model;

import java.io.Serializable;

public enum Semester implements Serializable {
    SECOND("Второй"),
    THIRD("Третий"),
    FIFTH("Пятый"),
    SEVENTH("Седьмой"),
    EIGHTH("Восьмой");

    public final String name;

    Semester(String name) {
        this.name = name;
    }
}
