package us.obviously.itmo.prog.exceptions;

/**
 * Класс исключения, которое будет выброшено, если в команду передан неверный аргумент
 */
public class InvalidArgumentException extends Exception {
    /**
     * Конструктор со специальным сообщением о деталях исключения
     * @param errorMessage Строка, содержащая детали исключения
     */
    public InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
