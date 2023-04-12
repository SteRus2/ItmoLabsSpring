package us.obviously.itmo.prog.common.model;

public enum Semester {
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
