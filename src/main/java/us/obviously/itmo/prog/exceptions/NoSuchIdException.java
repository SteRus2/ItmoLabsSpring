package us.obviously.itmo.prog.exceptions;

/**
 * Класс исключения, которое будет выброшено, если в коллекции не будет нужного ID
 */
public class NoSuchIdException extends Exception {
    /**
     * Конструктор со специальным сообщением о деталях исключения
     *
     * @param err Строка, содержащая детали исключения
     */
    public NoSuchIdException(String err) {
        super(err);
    }
}
