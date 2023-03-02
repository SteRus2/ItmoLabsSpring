package us.obviously.itmo.prog.model;

public enum Semester {
    SECOND ("Второй"),
    THIRD ("Третий"),
    FIFTH ("Пятый"),
    SEVENTH ("Седьмой"),
    EIGHTH ("Восьмой");

    public final String name;

    Semester(String name) {
        this.name = name;
    }
}
