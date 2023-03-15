package us.obviously.itmo.prog.exceptions;

/**
 * Класс исключения, которое будет выброшено, если данные не получится прочитать файл в коллекцию
 */
public class CantParseDataException extends Exception{
    /**
     * Конструктор со специальным сообщением о деталях исключения
     * @param errMsg Строка, содержащая детали исключения
     */
    public CantParseDataException(String errMsg) {super(errMsg);}
}
