package us.obviously.itmo.prog.exceptions;

/**
 * Класс исключения, которое будет выброшено, если execute_script начал вызывать сам себя
 */
public class RecurrentExecuteScripts extends Exception {
    /**
     * Конструктор со специальным сообщением о деталях исключения
     * @param errorMessage Строка, содержащая детали исключения
     */
    public RecurrentExecuteScripts(String errorMessage) {
        super(errorMessage);
    }
}
