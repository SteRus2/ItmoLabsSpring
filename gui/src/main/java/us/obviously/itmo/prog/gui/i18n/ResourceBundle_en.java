package us.obviously.itmo.prog.gui.i18n;

import java.util.ListResourceBundle;

public class ResourceBundle_en extends ListResourceBundle {
    private final Object[][] contents = {
            {"loading", "Loading..."},
            {"errors.resourceLoadingError", "Loading Resources Error"},

            {"languages.ru", "Рашн"},
            {"languages.en", "English"},
            {"languages.ua", "Український"},
            {"languages.ro", "Română"},
            {"languages.es_SV", "Español"},

            {"connection.signIn", "Sign In"},
            {"connection.signUp", "Sign Up"},
            {"connection.repeat", "Try again"},
            {"signIn.title", "Sign In"},
            {"signIn.HaveNoAccount", "Don't have an account? Register"},
            {"signIn.usernamePrompt", "Username"},
            {"signIn.passwordPrompt", "Password"},
            {"signIn.submitButton", "Sign In"},
            {"signIn.errors.invalidCredentials", "Invalid Credentials."},
            {"signIn.errors.blankUsername", "Username cannot be blank."},
            {"signIn.errors.blankPassword", "Password cannot be blank."},

            {"mainTable.columns.idColumn", "ID"},
            {"mainTable.columns.nameColumn", "Name"},
            {"mainTable.columns.coordinatesXColumn", "X"},
            {"mainTable.columns.coordinatesYColumn", "Y"},
            {"mainTable.columns.creationDateColumn", "Creation Date"},
            {"mainTable.columns.studentsCountColumn", "Students Count"},
            {"mainTable.columns.formOfEducationColumn", "Form Of Education"},
            {"mainTable.columns.semesterColumn", "Semester"},
            {"mainTable.columns.groupAdminColumn", "Group Admin"},
            {"mainTable.columns.groupAdminNameColumn", "GA Name"},
            {"mainTable.columns.groupAdminBirthdayColumn", "GA Name"},
            {"mainTable.columns.ownerIdColumn", "Owner ID"},
            {"mainTable.columns.ownerUsernameColumn", "Owner Username"},
    };

    public Object[][] getContents() {
        return contents;
    }
}
