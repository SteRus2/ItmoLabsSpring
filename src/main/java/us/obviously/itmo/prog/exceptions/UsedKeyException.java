package us.obviously.itmo.prog.exceptions;

/**
 * Класс исключения, которое будет выброшено, если данный ключ уже используется
 */
public class UsedKeyException extends Exception{
    /**
     * Конструктор со специальным сообщением о деталях исключения
     * @param err Строка, содержащая детали исключения
     */
    public UsedKeyException(String err){
        super(err);
    }
}
