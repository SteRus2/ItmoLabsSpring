package us.obviously.itmo.prog.common.model;

import java.io.Serializable;

public enum Country implements Serializable {
    FRANCE("Франция", "country.france"),
    INDIA("Индия", "country.india"),
    VATICAN("Ватикан", "country.vatican"),
    THAILAND("Таиланд", "country.thailand"),
    JAPAN("Япония", "country.japan");

    public final String name;
    public final String key;

    Country(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public static Country[] getOptions() {
        return new Country[]{
                null,
                FRANCE,
                INDIA,
                VATICAN,
                THAILAND,
                JAPAN
        };
    }
}
