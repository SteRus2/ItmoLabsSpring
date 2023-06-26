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
            {"formats.date", "MM/dd/yyyy"},
            {"formats.zonedDate", "MM/dd/yyyy - hh:mm"},

            {"loading", "Loading..."},
            {"errors.resourceLoadingError", "Error loading page"},

            {"sidebar.logout", "Logout"},

            {"connection.signIn", "Sign in"},
            {"connection.signUp", " Sign Up"},
            {"connection.repeat", "Try again"},
            {"signIn.title", "Input"},
            {"signIn.HaveNoAccount", "Don't have an account? Sign up"},
            {"signIn.usernamePrompt", "Username"},
            {"signIn.passwordPrompt", "Password"},
            {"signIn.submitButton", "Sign In"},
            {"signIn.errors.invalidCredentials", "Invalid Credentials."},
            {"signIn.errors.blankUsername", "Username cannot be blank."},
            {"signIn.errors.blankPassword", "Password cannot be blank."},

            {"signUp.title", "Registration"},
            {"signUp.haveAccount", "Got an account? Sign in"},
            {"signUp.usernamePrompt", "Username"},
            {"signUp.passwordPrompt", "Password"},
            {"signUp.submitButton", "Sign Up"},
            {"signUp.errors.invalidCredentials", "Invalid Credentials (Name taken)."},
            {"signUp.errors.blankUsername", "Username cannot be blank."},
            {"signUp.errors.blankPassword", "Password cannot be blank."},

            {"mainTable.columns.idColumn", "ID"},
            {"mainTable.columns.nameColumn", "Name"},
            {"mainTable.columns.coordinatesXColumn", "X"},
            {"mainTable.columns.coordinatesYColumn", "Y"},
            {"mainTable.columns.creationDateColumn", "Date of creation"},
            {"mainTable.columns.studentsCountColumn", "Number of students"},
            {"mainTable.columns.formOfEducationColumn", "Education Form"},
            {"mainTable.columns.semesterColumn", "Semester"},
            {"mainTable.columns.groupAdminNameColumn", "Admin name"},
            {"mainTable.columns.groupAdminBirthdayColumn", "admin DR"},
            {"mainTable.columns.groupAdminEyeColorColumn", "Admin eyes"},
            {"mainTable.columns.groupAdminHairColorColumn", "Hair"},
            {"mainTable.columns.groupAdminNationalityColumn", "Admin skin"},
            {"mainTable.columns.ownerIdColumn", "Owner ID"},
            {"mainTable.columns.ownerUsernameColumn", "Owner Username"},

            {"tools.add", "Add"},
            {"tools.delete", "Delete"},
            {"tools.replace", "Replace"},
            {"tools.clear", "Clear"},
            {"tools.executeScript", "Execute script"},
            {"tools.groupByName", "Group By Name"},
            {"tools.serviceInformation", "Service Information"},
            {"tools.filterByGroupAdmin", "Filter by group admin"},
            {"tools.meaningsOfSemesters", "Semesters values"},

            {"tool.add.submit", "Thank you"},
            {"tool.add.cancel", "Cancel thanks"},

            {"tool.update.submit", "Thank you"},
            {"tool.update.cancel", "Cancel thanks"},
            {"tool.update.delete", "No longer needed, thanks"},
            {"tool.update.delete.success.title", "Deleted successfully"},
            {"tool.update.delete.success.message", "Please"},

            {"tool.filterByAdmin.submit", "Thank you"},
            {"tool.filterByAdmin.cancel", "Cancel thanks"},
            {"tool.filterByAdmin.delete", "No longer needed, thanks"},

            {"field.groupName", "Group name"},
            {"field.groupName.errors.blank", "Cannot be empty"},
            {"field.coordinates.x", "X-coordinate"},
            {"field.coordinates.x.errors.null", "Cannot be null"},
            {"field.coordinates.y", "Y-coordinate"},
            {"field.coordinates.y.errors.null", "Cannot be null"},
            {"field.studentsCount", "Number of students"},
            {"field.studentsCount.errors.min", "Cannot be less than 1"},
            {"field.formOfEducation", "Format of education"},
            {"field.semester", "Semester"},
            {"field.semester.null", "None"},
            {"field.admin.name", "Admin name"},
            {"field.admin.birthday", "Admin's birthday"},
            {"field.admin.eyeColor", "Admin eyes"},
            {"field.admin.hairColor", "Admin Hair"},
            {"field.admin.nationality", "Admin Skin"},
            {"field.replaceIfGreater", "Replace only if new is greater than previous"},
            {"field.adminFilterEnabled", "Filter is enabled, thanks"},

            {"semester.second", "Second"},
            {"semester.third", "Third"},
            {"semester.fifth", "Fifth"},
            {"semester.seventh", "Seventh"},
            {"semester.eighth", "eighth"},

            {"formOfEducation.distanceEduction", "Distance Education"},
            {"formOfEducation.fullTimeEducation", "Full full time education"},
            {"formOfEducation.eveningClasses", "Evening Classes"},

            {"country.india", "India"},
            {"country.vatican", "Vatican"},
            {"country.thailand", "Thailand"},
            {"country.japan", "Japan"},
            {"country.france", "France"},

            {"admin.nationality.india", "India"},
            {"admin.nationality.vatican", "Vatican"},
            {"admin.nationality.thailand", "Thailand"},
            {"admin.nationality.japan", "Japan"},
            {"admin.nationality.france", "France"},
            {"admin.nationality.null", "None"},

            {"color.red", "Red"},
            {"color.yellow", "Yellow"},
            {"color.orange", "Orange"},
            {"color.white", "White"},

            {"admin.eyeColor.red", "Red"},
            {"admin.eyeColor.yellow", "Yellow"},
            {"admin.eyeColor.orange", "Orange"},
            {"admin.eyeColor.white", "White"},
            {"admin.eyeColor.null", "None"},

            {"admin.hairColor.red", "Red"},
            {"admin.hairColor.yellow", "Yellow"},
            {"admin.hairColor.orange", "Orange"},
            {"admin.hairColor.white", "White"},
            {"admin.hairColor.null", "None"},

            {"formOfEducation.null", "Not Specified"},
            {"semester.null", "Not Specified"},
            {"eyeColor.null", "Not Specified"},
            {"hairColor.null", "Not Specified"},
            {"nationality.null", "Not Specified"},
            {"admin.eyeColor.null", "Not Specified"},
            {"admin.hairColor.null", "Not Specified"},
            {"admin.nationality.null", "Not Specified"},

            {"files.executeScript", "Execute script"}
    };

    public Object[][] getContents() {
        return contents;
    }
}
