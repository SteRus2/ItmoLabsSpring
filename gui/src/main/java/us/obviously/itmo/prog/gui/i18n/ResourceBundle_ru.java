package us.obviously.itmo.prog.gui.i18n;

import java.util.ListResourceBundle;

public class ResourceBundle_ru extends ListResourceBundle {

    private final Object[][] contents = {
            {"loading", "Загрузка..."},
            {"errors.resourceLoadingError", "Ошибка при загрузке страницы"},

            {"formats.date", "dd.MM.yyyy"},
            {"formats.zonedDate", "dd.MM.yyyy - hh:mm"},

            {"languages.ru", "Русский"},
            {"languages.en", "English"},
            {"languages.ua", "Український"},
            {"languages.ro", "Română"},
            {"languages.es_SV", "Español"},

            {"sidebar.logout", "Выйти"},

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

            {"mainTable.columns.idColumn", "ID"},
            {"mainTable.columns.nameColumn", "Название"},
            {"mainTable.columns.coordinatesXColumn", "X"},
            {"mainTable.columns.coordinatesYColumn", "Y"},
            {"mainTable.columns.creationDateColumn", "Дата создания"},
            {"mainTable.columns.studentsCountColumn", "Количество студентов"},
            {"mainTable.columns.formOfEducationColumn", "Форма обучения"},
            {"mainTable.columns.semesterColumn", "Семестр"},
            {"mainTable.columns.groupAdminNameColumn", "Имя админа"},
            {"mainTable.columns.groupAdminBirthdayColumn", "ДР админа"},
            {"mainTable.columns.groupAdminEyeColorColumn", "Глаза админа"},
            {"mainTable.columns.groupAdminHairColorColumn", "Волосы"},
            {"mainTable.columns.groupAdminNationalityColumn", "Кожа админа"},
            {"mainTable.columns.ownerIdColumn", "ID владельца"},
            {"mainTable.columns.ownerUsernameColumn", "Имя пользователя владельца"},

            {"tools.add", "Добавить"},
            {"tools.delete", "Удалить"},
            {"tools.replace", "Заменить"},
            {"tools.clear", "Очистить"},
            {"tools.executeScript", "Исполнить скрипт"},
            {"tools.groupByName", "Группировка по имени"},
            {"tools.serviceInformation", "Служебная информация"},
            {"tools.filterByGroupAdmin", "Фильтр по админу группы"},
            {"tools.meaningsOfSemesters", "Значения семестров"},

            {"tool.add.submit", "Спасибо"},
            {"tool.add.cancel", "Отмена, спасибо"},

            {"tool.update.submit", "Спасибо"},
            {"tool.update.cancel", "Отмена, спасибо"},
            {"tool.update.delete", "Больше не нужен, спасибо"},
            {"tool.update.delete.success.title", "Успешно удалено"},
            {"tool.update.delete.success.message", "Пожалуйста"},

            {"tool.filterByAdmin.submit", "Спасибо"},
            {"tool.filterByAdmin.cancel", "Отмена, спасибо"},
            {"tool.filterByAdmin.delete", "Больше не нужен, спасибо"},

            {"field.groupName", "Название группы"},
            {"field.groupName.errors.blank", "Не может быть пустым"},
            {"field.coordinates.x", "Координата X"},
            {"field.coordinates.x.errors.null", "Не может быть null"},
            {"field.coordinates.y", "Координата Y"},
            {"field.coordinates.y.errors.null", "Не может быть null"},
            {"field.studentsCount", "Количество студентов"},
            {"field.studentsCount.errors.min", "Не может быть меньше 1"},
            {"field.formOfEducation", "Формат обучения"},
            {"field.semester", "Семестр"},
            {"field.semester.null", "Нет его"},
            {"field.admin.name", "Имя админа"},
            {"field.admin.birthday", "День рождения админа"},
            {"field.admin.eyeColor", "Глазки админа"},
            {"field.admin.hairColor", "Волосы админа"},
            {"field.admin.nationality", "Кожа админа"},
            {"field.replaceIfGreater", "Заменить только если новый больше предыдущего"},
            {"field.adminFilterEnabled", "Фильтр активен, спасибо"},

            {"semester.second", "Второй"},
            {"semester.third", "Третий"},
            {"semester.fifth", "Пятый"},
            {"semester.seventh", "Седьмой"},
            {"semester.eighth", "Восьмой"},

            {"formOfEducation.distanceEduction", "Дистанционное обучение"},
            {"formOfEducation.fullTimeEducation", "Полное очное обучение"},
            {"formOfEducation.eveningClasses", "Вечернее обучение"},

            {"country.india", "Индия"},
            {"country.vatican", "Ватикан"},
            {"country.thailand", "Таиланд"},
            {"country.japan", "Япония"},
            {"country.france", "Франция"},

            {"admin.nationality.india", "Индия"},
            {"admin.nationality.vatican", "Ватикан"},
            {"admin.nationality.thailand", "Таиланд"},
            {"admin.nationality.japan", "Япония"},
            {"admin.nationality.france", "Франция"},
            {"admin.nationality.null", "Нет её"},

            {"color.red", "Красный"},
            {"color.yellow", "Жёлтый"},
            {"color.orange", "Оранжевый"},
            {"color.white", "Белый"},

            {"admin.eyeColor.red", "Красный"},
            {"admin.eyeColor.yellow", "Жёлтый"},
            {"admin.eyeColor.orange", "Оранжевый"},
            {"admin.eyeColor.white", "Белый"},
            {"admin.eyeColor.null", "Нет их"},

            {"admin.hairColor.red", "Красный"},
            {"admin.hairColor.yellow", "Жёлтый"},
            {"admin.hairColor.orange", "Оранжевый"},
            {"admin.hairColor.white", "Белый"},
            {"admin.hairColor.null", "Нет их"},

            {"formOfEducation.null", "Не указан"},
            {"semester.null", "Не указан"},
            {"eyeColor.null", "Не указан"},
            {"hairColor.null", "Не указан"},
            {"nationality.null", "Не указан"},
            {"admin.eyeColor.null", "Не указан"},
            {"admin.hairColor.null", "Не указан"},
            {"admin.nationality.null", "Не указан"},

            {"files.executeScript", "Исполнить скрипт"}
    };

    public Object[][] getContents() {
        return contents;
    }
}
