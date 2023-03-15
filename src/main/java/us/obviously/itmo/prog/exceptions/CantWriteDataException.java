package us.obviously.itmo.prog.exceptions;

/**
 * Класс исключения, которые будет выброшено, если не получится записать данные
 */
public class CantWriteDataException extends Exception{
    /**
     * Конструктор со специальным сообщением о деталях исключения
     * @param errMsg Строка, содержащая детали исключения
     */
    public CantWriteDataException(String errMsg){super(errMsg);}
}
