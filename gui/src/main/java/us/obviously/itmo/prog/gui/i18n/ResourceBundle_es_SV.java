package us.obviously.itmo.prog.gui.i18n;

import java.util.ListResourceBundle;

// Spanish
public class ResourceBundle_es_SV extends ListResourceBundle {
    private final Object[][] contents = {
            {"loading", "Cargando..."},
            {"errors.resourceLoadingError", "Error al cargar la página"},

            {"languages.ru", "Русо"},
            {"languages.en", "English"},
            {"languages.ua", "Український"},
            {"languages.ro", "Română"},
            {"languages.es_SV", "Español"},

            {"connection.signIn", "Iniciar sesión"},
            {"connection.signUp", "Registrarse"},
            {"connection.repeat", "Registrarse"},
            {"signIn.title", "Iniciar sesión"},
            {"signIn.HaveNoAccount", "¿No tienes una cuenta? Regístrate"},
            {"signIn.usernamePrompt", "Nombre de usuario"},
            {"signIn.passwordPrompt", "Contraseña"},
            {"signIn.errors.invalidCredentials", "Credenciales inválidas."},
            {"signIn.errors.blankUsername", "El nombre de usuario no puede estar vacío."},
            {"signIn.errors.blankPassword", "La contraseña no puede estar vacía."},
    };

    public Object[][] getContents() {
        return contents;
    }
}
