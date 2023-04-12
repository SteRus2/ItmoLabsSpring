package us.obviously.itmo.prog.client.exceptions;

/**
 * Класс исключения, которое будет выброшено, если объект не проходит валидацию
 */
public class IncorrectValueException extends Exception {
    /**
     * Конструктор со специальным сообщением о деталях исключения
     *
     * @param errorMessage Строка, содержащая детали исключения
     */
    public IncorrectValueException(String errorMessage) {
        super(errorMessage);
    }
}
