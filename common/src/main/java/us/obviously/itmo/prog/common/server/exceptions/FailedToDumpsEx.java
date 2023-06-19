package us.obviously.itmo.prog.common.server.exceptions;

import java.io.IOException;

/**
 * Класс исключения, которое будет выброшено, если данные не получится перевести в нужный формат
 */
public class FailedToDumpsEx extends IOException {
    /**
     * Конструктор со специальным сообщением о деталях исключения
     *
     * @param err Строка, содержащая детали исключения
     */
    public FailedToDumpsEx(String err) {
        super(err);
    }
}
