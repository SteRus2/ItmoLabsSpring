package us.obviously.itmo.prog.gui.i18n;

import java.util.ListResourceBundle;

public class ResourceBundle_ru extends ListResourceBundle {

    private final Object[][] contents = {
            {"loading", "Загрузка..."},
            {"errors.resourceLoadingError", "Ошибка при загрузке страницы"},

            {"languages.ru", "Русский"},
            {"languages.en", "English"},
            {"languages.ua", "Український"},
            {"languages.ro", "Română"},
            {"languages.es_SV", "Español"},

            {"connection.signIn", "Войти"},
            {"connection.signUp", "Зарегистрироваться"},
            {"connection.repeat", "Попробовать снова"},
            {"signIn.title", "Вход"},
            {"signIn.HaveNoAccount", "Нет аккаунта? Зарегистрироваться"},
            {"signIn.usernamePrompt", "Имя пользователя"},
            {"signIn.passwordPrompt", "Пароль"},
            {"signIn.submitButton", "Войти"},
            {"signIn.errors.invalidCredentials", "Неверные учетные данные."},
            {"signIn.errors.blankUsername", "Имя пользователя не может быть пустым."},
            {"signIn.errors.blankPassword", "Пароль не может быть пустым."},

            {"signUp.title", "Регистрация"},
            {"signUp.haveAccount", "Есть аккаунт? Войти"},
            {"signUp.usernamePrompt", "Имя пользователя"},
            {"signUp.passwordPrompt", "Пароль"},
            {"signUp.submitButton", "Регистрация"},
            {"signUp.errors.invalidCredentials", "Неверные учетные данные (имя занято)."},
            {"signUp.errors.blankUsername", "Имя пользователя не может быть пустым."},
            {"signUp.errors.blankPassword", "Пароль не может быть пустым."},

            {"mainTable.columns.idColumn", "Идентификатор"},
            {"mainTable.columns.nameColumn", "Название"},
            {"mainTable.columns.coordinatesXColumn", "X"},
            {"mainTable.columns.coordinatesYColumn", "Y"},
            {"mainTable.columns.creationDateColumn", "Дата создания"},
            {"mainTable.columns.studentsCountColumn", "Количество студентов"},
            {"mainTable.columns.formOfEducationColumn", "Форма обучения"},
            {"mainTable.columns.semesterColumn", "Семестр"},
            {"mainTable.columns.groupAdminColumn", "Администратор группы"},
            {"mainTable.columns.groupAdminNameColumn", "Имя администратора группы"},
            {"mainTable.columns.groupAdminBirthdayColumn", "Имя администратора группы"},
            {"mainTable.columns.ownerIdColumn", "Идентификатор владельца"},
            {"mainTable.columns.ownerUsernameColumn", "Имя пользователя владельца"},
    };

    public Object[][] getContents() {
        return contents;
    }
}
