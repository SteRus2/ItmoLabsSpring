package us.obviously.itmo.prog.console;

public class Messages {
    static public void print(String text, Object ... args) {
        System.out.printf(text, args);
    }

    static public void printStatement(String text, Object ... args) {
        System.out.printf(text + "%n", args);
    }
}
