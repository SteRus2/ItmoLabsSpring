package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.manager.Management;

import java.util.HashMap;

public class InfoCommand extends AbstractCommand {

    public InfoCommand(Management manager) {
        super(manager, "info", "Вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
    }

    @Override
    public void execute(HashMap<String, String> args) {
        var info = this.manager.getDataCollection().getInfo();
        System.out.println("Количество: " + ConsoleColors.BLUE + info.getCount() + ConsoleColors.RESET);
        System.out.println("       Тип: " + ConsoleColors.BLUE + info.getType() + ConsoleColors.RESET);
        System.out.println("      Дата: " + ConsoleColors.BLUE + info.getDate() + ConsoleColors.RESET);
    }
}
