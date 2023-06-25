package us.obviously.itmo.prog.gui.i18n;

import java.util.ListResourceBundle;

public class ResourceBundle_ua extends ListResourceBundle {

    private final Object[][] contents = {
            {"loading", "Завантаження..."},
            {"errors.resourceLoadingError", "Помилка при завантаженні сторінки"},

            {"languages.ru", "Російська"},
            {"languages.en", "English"},
            {"languages.ua", "Український"},
            {"languages.ro", "Română"},
            {"languages.es_SV", "Español"},

            {"connection.signIn", "Увійти"},
            {"connection.signUp", "Зареєструватися"},
            {"signIn.title", "Увійти"},
            {"signIn.HaveNoAccount", "Немає облікового запису? Зареєструватися"},
            {"signIn.usernamePrompt", "Ім'я користувача"},
            {"signIn.passwordPrompt", "Пароль"},
            {"signIn.errors.invalidCredentials", "Невірні облікові дані."},
            {"signIn.errors.blankUsername", "Ім'я користувача не може бути порожнім."},
            {"signIn.errors.blankPassword", "Пароль не може бути порожнім."},
    };

    public Object[][] getContents() {
        return contents;
    }
}
