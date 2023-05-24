package us.obviously.itmo.prog.client.console;

/**
 * Класс для вывода сообщений в консоль
 */
public class Messages {
    /**
     * Вывод отформатированного текста с подстановкой цветов
     *
     * @param text Текст
     * @param args Аргументы для форматирования
     * @see ConsoleColor
     */
    static public void print(String text, Object... args) {
        for (var color : ConsoleColor.getColors()) {
            text = text.replaceAll("~" + color.regex, color.code);
        }
        System.out.printf(text, args);
    }

    /**
     * Вывод отформатированного текста с подстановкой цветов и новая строка в конце
     *
     * @param text Текст
     * @param args Аргументы для форматирования
     * @see Messages#print(String, Object...)
     */
    static public void printStatement(String text, Object... args) {
        print(text + "%n", args);
    }
}
