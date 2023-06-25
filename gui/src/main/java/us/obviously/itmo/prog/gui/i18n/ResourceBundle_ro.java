package us.obviously.itmo.prog.gui.i18n;

import java.util.ListResourceBundle;

public class ResourceBundle_ro extends ListResourceBundle {
    private final Object[][] contents = {
            {"loading", "Se încarcă..."},
            {"errors.resourceLoadingError", "Eroare la încărcarea paginii"},

            {"languages.ru", "Руса"},
            {"languages.en", "English"},
            {"languages.ua", "Український"},
            {"languages.ro", "Română"},
            {"languages.es_SV", "Español"},

            {"connection.signIn", "Conectare"},
            {"connection.signUp", "Înregistrare"},
            {"signIn.title", "Conectare"},
            {"signIn.HaveNoAccount", "Nu aveți un cont? Înregistrați-vă"},
            {"signIn.usernamePrompt", "Nume de utilizator"},
            {"signIn.passwordPrompt", "Parolă"},
            {"signIn.errors.invalidCredentials", "Date de autentificare incorecte."},
            {"signIn.errors.blankUsername", "Numele de utilizator nu poate fi gol."},
            {"signIn.errors.blankPassword", "Parola nu poate fi goală."},
    };

    public Object[][] getContents() {
        return contents;
    }
}
