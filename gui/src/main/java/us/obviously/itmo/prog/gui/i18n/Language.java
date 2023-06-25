package us.obviously.itmo.prog.gui.i18n;

import java.util.Locale;

public enum Language {
    RUSSIAN(new Locale("ru")),
    ENGLISH(Locale.ENGLISH),
    UKRAINIAN(new Locale("ua")),
    ROMANIAN(new Locale("ro")),
    SPANISH(new Locale("es", "SV"));

    private final static String prefix = "languages.";

    private final Locale locale;
    private final String key;

    Language(Locale locale) {
        this.locale = locale;
        this.key = createKey();
    }

    public Locale getLocale() {
        return locale;
    }

    private String createKey() {
        var language = getLocale().getLanguage();
        var country = getLocale().getCountry();
        if (country != null && !country.equals("")) {
            return prefix + language + "_" + country;
        }
        return prefix + language;
    }

    public String getKey() {
        return key;
    }
}
