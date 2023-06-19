package us.obviously.itmo.prog.common.server.exceptions;

/**
 * Класс исключения, которое будет выброшено, если в команду передано больше аргументов, чем предусмотрено
 */
public class UnexpectedArgumentException extends Exception {
    /**
     * Конструктор со специальным сообщением о деталях исключения
     *
     * @param errorMessage Строка, содержащая детали исключения
     */
    public UnexpectedArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
