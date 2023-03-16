package us.obviously.itmo.prog.exceptions;

/**
 * Класс исключения, которое будет выброшено, если в команду не передан обязательный аргумент
 */
public class MissedArgumentException extends Exception {
    /**
     * Конструктор со специальным сообщением о деталях исключения
     *
     * @param errorMessage Строка, содержащая детали исключения
     */
    public MissedArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
