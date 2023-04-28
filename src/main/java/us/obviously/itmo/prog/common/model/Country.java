package us.obviously.itmo.prog.common.model;

import java.io.Serializable;

public enum Country implements Serializable {
    FRANCE("Франция"),
    INDIA("Индия"),
    VATICAN("Ватикан"),
    THAILAND("Таиланд"),
    JAPAN("Япония");

    public final String name;

    Country(String name) {
        this.name = name;
    }
}
