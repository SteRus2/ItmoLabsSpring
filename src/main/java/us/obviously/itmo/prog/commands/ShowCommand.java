package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.manager.Management;

import java.util.HashMap;

public class ShowCommand extends AbstractCommand {
    public ShowCommand(Management manager) {
        super(manager, "show", "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
    }

    /**
     *
     */
    @Override
    public void execute(HashMap<String, String> args) {

    }
}
