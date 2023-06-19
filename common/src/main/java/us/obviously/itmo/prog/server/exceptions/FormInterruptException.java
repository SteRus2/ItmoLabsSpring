package us.obviously.itmo.prog.server.exceptions;

/**
 * Класс исключения, которое будет выброшено, когда пользователь при заполнении слова введёт команду прерывания (по умолчания <b>/exit</b?>)
 */
public class FormInterruptException extends Exception {
    /**
     * Конструктор со специальным сообщением о деталях исключения
     *
     * @param errorMessage Строка, содержащая детали исключения
     */
    public FormInterruptException(String errorMessage) {
        super(errorMessage);
    }
}
