package us.obviously.itmo.prog.gui.i18n;

import java.util.ListResourceBundle;

public class ResourceBundle_ua extends ListResourceBundle {

    private final Object[][] contents = {
            {"languages.ru", "Російська"},
            {"languages.en", "English"},
            {"languages.ua", "Український"},
            {"languages.ro", "Română"},
            {"languages.es_SV", "Español"}, {"formats.date", "dd.MM.yyyy"},
            {"formats.zonedDate", "dd.MM.yyyy - hh:mm"},

            {"loading", "Завантаження..."},
            {"errors.resourceLoadingError", "Помилка при завантаженні сторінки"},

            {"sidebar.logout", "Вийти"},

            {"connection.signIn", "Увійти"},
            {"connection.signUp", "Зареєструватися"},
            {"connection.repeat", "Спробувати знову"},
            {"signIn.title", "Вхід"},
            {"signIn.HaveNoAccount", "Немає облікового запису? Зареєструватися"},
            {"signIn.usernamePrompt", "Ім'я користувача"},
            {"signIn.passwordPrompt", "Пароль"},
            {"signIn.submitButton", "Увійти"},
            {"signIn.errors.invalidCredentials", "Невірні облікові дані."},
            {"signIn.errors.blankUsername", "Ім'я користувача не може бути порожнім."},
            {"signIn.errors.blankPassword", "Пароль не може бути порожнім."},

            {"signUp.title", "Реєстрація"},
            {"signUp.haveAccount", "Є обліковий запис? Увійти"},
            {"signUp.usernamePrompt", "Ім'я користувача"},
            {"signUp.passwordPrompt", "Пароль"},
            {"signUp.submitButton", "Реєстрація"},
            {"signUp.errors.invalidCredentials", "Невірні облікові дані (ім'я зайняте)."},
            {"signUp.errors.blankUsername", "Ім'я користувача не може бути порожнім."},
            {"signUp.errors.blankPassword", "Пароль не може бути порожнім."},

            {"mainTable.columns.idColumn", "ID"},
            {"mainTable.columns.nameColumn", "Назва"},
            {"mainTable.columns.coordinatesXColumn", "X"},
            {"mainTable.columns.coordinatesYColumn", "Y"},
            {"mainTable.columns.creationDateColumn", "Дата створення"},
            {"mainTable.columns.studentsCountColumn", "Кількість студентів"},
            {"mainTable.columns.formOfEducationColumn", "Форма навчання"},
            {"mainTable.columns.semesterColumn", "Семестр"},
            {"mainTable.columns.groupAdminNameColumn", "Ім'я адміна"},
            {"mainTable.columns.groupAdminBirthdayColumn", "ДР адміну"},
            {"mainTable.columns.groupAdminEyeColorColumn", "Очі адміна"},
            {"mainTable.columns.groupAdminHairColorColumn", "Волосся"},
            {"mainTable.columns.groupAdminNationalityColumn", "Шкіра адміну"},
            {"mainTable.columns.ownerIdColumn", "ID власника"},
            {"mainTable.columns.ownerUsernameColumn", "Ім'я користувача власника"},

            {"tools.add", "Додати"},
            {"tools.delete", "Видалити"},
            {"tools.replace", "Замінити"},
            {"tools.clear", "Очистити"},
            {"tools.executeScript", "Виконати скрипт"},
            {"tools.groupByName", "Угруповання на ім'я"},
            {"tools.serviceInformation", "Службова інформація"},
            {"tools.filterByGroupAdmin", "Фільтр за адміністрацією групи"},
            {"tools.meaningsOfSemesters", "Значення семестрів"},

            {"tool.add.submit", "Дякую"},
            {"tool.add.cancel", "Скасувати, спасибі"},

            {"tool.update.submit", "Спасибі"},
            {"tool.update.cancel", "Скасувати, спасибі"},
            {"tool.update.delete", "Більше не потрібний, спасибі"},
            {"tool.update.delete.success.title", "Успішно видалено"},
            {"tool.update.delete.success.message", "Будь ласка"},

            {"tool.filterByAdmin.submit", "Дякую"},
            {"tool.filterByAdmin.cancel", "Скасувати, спасибі"},
            {"tool.filterByAdmin.delete", "Більше не потрібний, спасибі"},

            {"field.groupName", "Назва групи"},
            {"field.groupName.errors.blank", "Не може бути порожнім"},
            {"field.coordinates.x", "Координата X"},
            {"field.coordinates.x.errors.null", "Не може бути null"},
            {"field.coordinates.y", "Координата Y"},
            {"field.coordinates.y.errors.null", "Не може бути null"},
            {"field.studentsCount", "Кількість студентів"},
            {"field.studentsCount.errors.min", "Не може бути менше 1"},
            {"field.formOfEducation", "Формат навчання"},
            {"field.semester", "Семестр"},
            {"field.semester.null", "Немає його"},
            {"field.admin.name", "Ім'я адміну"},
            {"field.admin.birthday", "День народження адміну"},
            {"field.admin.eyeColor", "Очі адміну"},
            {"field.admin.hairColor", "Волоси адміну"},
            {"field.admin.nationality", "Шкіра адміну"},
            {"field.replaceIfGreater", "Замінити тільки якщо новий більше попереднього"},
            {"field.adminFilterEnabled", "Фільтр активний, дякую"},

            {"semester.second", "Другий"},
            {"semester.third", "Третій"},
            {"semester.fifth", "П'ятий"},
            {"semester.seventh", "Сьомий"},
            {"semester.eighth", "Восьмий"},

            {"formOfEducation.distanceEduction", "Дистанційне навчання"},
            {"formOfEducation.fullTimeEducation", "Повне очне навчання"},
            {"formOfEducation.eveningClasses", "Вечірнє навчання"},

            {"country.india", "Індія"},
            {"country.vatican", "Ватикан"},
            {"country.thailand", "Таїланд"},
            {"country.japan", "Японія"},
            {"country.france", "Франція"},

            {"admin.nationality.india", "Індія"},
            {"admin.nationality.vatican", "Ватикан"},
            {"admin.nationality.thailand", "Таїланд"},
            {"admin.nationality.japan", "Японія"},
            {"admin.nationality.france", "Франція"},
            {"admin.nationality.null", "Немає її"},

            {"color.red", "Червоний"},
            {"color.yellow", "Жовтий"},
            {"color.orange", "Помаранчевий"},
            {"color.white", "Білий"},

            {"admin.eyeColor.red", "Червоний"},
            {"admin.eyeColor.yellow", "Жовтий"},
            {"admin.eyeColor.orange", "Помаранчевий"},
            {"admin.eyeColor.white", "Білий"},
            {"admin.eyeColor.null", "Немає їх"},

            {"admin.hairColor.red", "Червоний"},
            {"admin.hairColor.yellow", "Жовтий"},
            {"admin.hairColor.orange", "Помаранчевий"},
            {"admin.hairColor.white", "Білий"},
            {"admin.hairColor.null", "Немає їх"},

            {"formOfEducation.null", "Не вказано"},
            {"semester.null", "Не вказано"},
            {"eyeColor.null", "Не вказано"},
            {"hairColor.null", "Не вказано"},
            {"nationality.null", "Не вказано"},
            {"admin.eyeColor.null", "Не вказано"},
            {"admin.hairColor.null", "Не вказано"},
            {"admin.nationality.null", "Не вказано"},

            {"files.executeScript", "Виконати скрипт"}
    };

    public Object[][] getContents() {
        return contents;
    }
}
