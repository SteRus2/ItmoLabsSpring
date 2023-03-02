package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.manager.Management;

import java.util.HashMap;

public class ReplaceIfGreaterCommand extends AbstractCommand {
    public ReplaceIfGreaterCommand(Management manager) {
        super(manager, "replace_if_greater", "Заменить значение по ключу, если новое значение больше старого");
        addParameter("key", "Значение ID");

    }

    /**
     * TODO: FILL
     */
    @Override
    public void execute(HashMap<String, String> args) {

    }

}
