package us.obviously.itmo.prog.console;

import java.util.ArrayList;
import java.util.List;

public class ConsoleColor {
    private static final List<ConsoleColor> colors = new ArrayList<>();

    static public void initColors() {
        new ConsoleColor("RESET", "\033[0m", "=");

        // Regular Colors
        new ConsoleColor("BLACK", "\033[0;30m", "bk");
        new ConsoleColor("RED", "\033[0;31m", "re");
        new ConsoleColor("GREEN", "\033[0;32m", "gr");
        new ConsoleColor("YELLOW", "\033[0;33m", "ye");
        new ConsoleColor("BLUE", "\033[0;34m", "bl");
        new ConsoleColor("PURPLE", "\033[0;35m", "pu");
        new ConsoleColor("CYAN", "\033[0;36m", "cy");
        new ConsoleColor("WHITE", "\033[0;37m", "wh");

        // Bold
        new ConsoleColor("BLACK_BOLD", "\033[1;30m", "#bk");
        new ConsoleColor("RED_BOLD", "\033[1;31m", "#re");
        new ConsoleColor("GREEN_BOLD", "\033[1;32m", "#gr");
        new ConsoleColor("YELLOW_BOLD", "\033[1;33m", "#ye");
        new ConsoleColor("BLUE_BOLD", "\033[1;34m", "#bl");
        new ConsoleColor("PURPLE_BOLD", "\033[1;35m", "#pu");
        new ConsoleColor("CYAN_BOLD", "\033[1;36m", "#cy");
        new ConsoleColor("WHITE_BOLD", "\033[1;37m", "#wh");

        // Underline
        new ConsoleColor("BLACK_UNDERLINED", "\033[4;30m", "_bk");
        new ConsoleColor("RED_UNDERLINED", "\033[4;31m", "_re");
        new ConsoleColor("GREEN_UNDERLINED", "\033[4;32m", "_gr");
        new ConsoleColor("YELLOW_UNDERLINED", "\033[4;33m", "_ye");
        new ConsoleColor("BLUE_UNDERLINED", "\033[4;34m", "_bl");
        new ConsoleColor("PURPLE_UNDERLINED", "\033[4;35m", "_pu");
        new ConsoleColor("CYAN_UNDERLINED", "\033[4;36m", "_cy");
        new ConsoleColor("WHITE_UNDERLINED", "\033[4;37m", "_wh");

        // Background
        new ConsoleColor("BLACK_BACKGROUND", "\033[40m", "BK");
        new ConsoleColor("RED_BACKGROUND", "\033[41m", "RE");
        new ConsoleColor("GREEN_BACKGROUND", "\033[42m", "GR");
        new ConsoleColor("YELLOW_BACKGROUND", "\033[43m", "YE");
        new ConsoleColor("BLUE_BACKGROUND", "\033[44m", "BL");
        new ConsoleColor("PURPLE_BACKGROUND", "\033[45m", "PU");
        new ConsoleColor("CYAN_BACKGROUND", "\033[46m", "CY");
        new ConsoleColor("WHITE_BACKGROUND", "\033[47m", "WH");

        // High Intensity
        new ConsoleColor("BLACK_BRIGHT", "\033[0;90m", "0bk");
        new ConsoleColor("RED_BRIGHT", "\033[0;91m", "0re");
        new ConsoleColor("GREEN_BRIGHT", "\033[0;92m", "0gr");
        new ConsoleColor("YELLOW_BRIGHT", "\033[0;93m", "0ye");
        new ConsoleColor("BLUE_BRIGHT", "\033[0;94m", "0bl");
        new ConsoleColor("PURPLE_BRIGHT", "\033[0;95m", "0pu");
        new ConsoleColor("CYAN_BRIGHT", "\033[0;96m", "0cy");
        new ConsoleColor("WHITE_BRIGHT", "\033[0;97m", "0wh");

        // Bold High Intensity
        new ConsoleColor("BLACK_BOLD_BRIGHT", "\033[1;90m", "#0bk");
        new ConsoleColor("RED_BOLD_BRIGHT", "\033[1;91m", "#0re");
        new ConsoleColor("GREEN_BOLD_BRIGHT", "\033[1;92m", "#0gr");
        new ConsoleColor("YELLOW_BOLD_BRIGHT", "\033[1;93m", "#0ye");
        new ConsoleColor("BLUE_BOLD_BRIGHT", "\033[1;94m", "#0bl");
        new ConsoleColor("PURPLE_BOLD_BRIGHT", "\033[1;95m", "#0pu");
        new ConsoleColor("CYAN_BOLD_BRIGHT", "\033[1;96m", "#0cy");
        new ConsoleColor("WHITE_BOLD_BRIGHT", "\033[1;97m", "#0wh");

        // High Intensity backgrounds
        new ConsoleColor("BLACK_BACKGROUND_BRIGHT", "\033[0;100m", "0BK");
        new ConsoleColor("RED_BACKGROUND_BRIGHT", "\033[0;101m", "0RE");
        new ConsoleColor("GREEN_BACKGROUND_BRIGHT", "\033[0;102m", "0GR");
        new ConsoleColor("YELLOW_BACKGROUND_BRIGHT", "\033[0;103m", "0YE");
        new ConsoleColor("BLUE_BACKGROUND_BRIGHT", "\033[0;104m", "0BL");
        new ConsoleColor("PURPLE_BACKGROUND_BRIGHT", "\033[0;105m", "0PU");
        new ConsoleColor("CYAN_BACKGROUND_BRIGHT", "\033[0;106m", "0CY");
        new ConsoleColor("WHITE_BACKGROUND_BRIGHT", "\033[0;107m", "0WH");
    }


    static List<ConsoleColor> getColors() {
        return colors;
    }

    final String name;
    final String code;
    final String regex;

    public ConsoleColor(String name, String code, String regex) {
        this.name = name;
        this.code = code;
        this.regex = regex;
        ConsoleColor.colors.add(this);
    }

    @Override
    public String toString() {
        return code;
    }
}