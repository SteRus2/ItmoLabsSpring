package us.obviously.itmo.prog.console;

public class Messages {
    static public void print(String text, Object ... args) {
        for (var color: ConsoleColor.getColors()) {
            text = text.replaceAll("~" + color.regex, color.code);
        }
        System.out.printf(text, args);
    }

    static public void printStatement(String text, Object ... args) {
        print(text + "%n", args);
    }
}
