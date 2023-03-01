package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.manager.Management;

import java.util.HashMap;

public class InfoCommand extends AbstractCommand {

    public InfoCommand(Management manager) {
        super(manager, "info", "Вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
    }

    @Override
    public void execute(HashMap<String, String> args) {

    }
}
