package us.obviously.itmo.prog.exceptions;

/**
 * Класс исключения, которе будет выброшено, если данные в файле будут иметь неверный формат
 */
public class IncorrectValuesTypeException extends Exception {
    /**
     * Конструктор со специальным сообщением о деталях исключения
     *
     * @param errorMsg Строка, содержащая детали исключения
     */
    public IncorrectValuesTypeException(String errorMsg) {
        super(errorMsg);
    }
}
