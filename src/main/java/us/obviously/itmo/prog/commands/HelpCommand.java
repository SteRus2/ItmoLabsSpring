package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.manager.Management;

import java.util.HashMap;

public class HelpCommand extends AbstractCommand {

    public HelpCommand(Management manager) {
        super(manager, "help", "Вывести справку по доступным командам");
        addParameter("command", "помощь по конкретной команде", false);
    }

    @Override
    public void execute(HashMap<String, String> args) {
        var commandName = args.get("command");
        if (commandName != null && !commandName.equals("")) {
            System.out.println(specificHelp(commandName));
        } else {
            System.out.println(commonHelp());
        }
    }

    private String specificHelp(String commandName) {
        var command = this.manager.getCommand(commandName);
        if (command == null) {
            return ("В качестве аргумента передано недействительное значение. \"%s\" не является командой. Введите " +
                    ConsoleColors.GREEN + "help" + ConsoleColors.RESET +
                    " для просмотра всех возможных команд. %n").formatted(commandName);
        } else
            return command.getHelp();
    }

    private String commonHelp() {
        var builder = new StringBuilder();
        builder.append("Для получения сведений об определенной команде наберите help <имя команды>%n".formatted());
        this.manager.getCommands().forEach((command) -> {
            builder.append(command.getDescription()).append("%n".formatted());
        });
        return builder.toString();
    }
}
