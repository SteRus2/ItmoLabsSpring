package us.obviously.itmo.prog.exceptions;

import java.io.FileNotFoundException;

/**
 * Класс исключения, которое будет выброшено, если файл не будет найден
 */
public class CantFindFileException extends FileNotFoundException {
    /**
     * Конструктор со специальным сообщением о деталях исключения
     *
     * @param s Строка, содержащая детали исключения
     */
    public CantFindFileException(String s) {
        super(s);
    }
}
