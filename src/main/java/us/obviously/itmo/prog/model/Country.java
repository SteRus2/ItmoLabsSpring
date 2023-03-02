package us.obviously.itmo.prog.model;

public enum Country {
    FRANCE ("Франция"),
    INDIA ("Индия"),
    VATICAN ("Ватикан"),
    THAILAND ("Таиланд"),
    JAPAN ("Япония");

    public final String name;

    Country(String name) {
        this.name = name;
    }
}
