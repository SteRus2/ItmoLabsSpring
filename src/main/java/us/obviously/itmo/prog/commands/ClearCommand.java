package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.manager.Management;

import java.util.HashMap;

public class ClearCommand extends AbstractCommand {
    public ClearCommand(Management manager) {
        super(manager, "clear", "Очистить коллекцию");

    }

    /**
     * TODO: FILL
     */
    @Override
    public void execute(HashMap<String, String> args) {

    }

}