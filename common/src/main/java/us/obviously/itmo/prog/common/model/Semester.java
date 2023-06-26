package us.obviously.itmo.prog.common.model;

import java.io.Serializable;

public enum Semester implements Serializable {
    SECOND("Второй", "semester.second"),
    THIRD("Третий", "semester.third"),
    FIFTH("Пятый", "semester.fifth"),
    SEVENTH("Седьмой", "semester.seventh"),
    EIGHTH("Восьмой", "semester.eighth");

    public final String name;
    public final String key;

    Semester(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public static Semester[] getOptions() {
        return new Semester[]{
                SECOND,
                THIRD,
                FIFTH,
                SEVENTH,
                EIGHTH
        };
    }
}
