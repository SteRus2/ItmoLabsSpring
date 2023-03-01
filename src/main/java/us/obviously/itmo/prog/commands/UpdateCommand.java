package us.obviously.itmo.prog.commands;


import us.obviously.itmo.prog.manager.Management;

import java.util.HashMap;

public class UpdateCommand extends AbstractCommand {
    public UpdateCommand(Management manager) {
        super(manager, "update", "обновить значение элемента коллекции, id которого равен заданному");
        addParameter("id", "Значение ID");
    }

    /**
     *
     */
    @Override
    public void execute(HashMap<String, String> args) {

    }
}