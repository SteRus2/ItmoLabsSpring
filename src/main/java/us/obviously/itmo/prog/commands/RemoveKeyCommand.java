package us.obviously.itmo.prog.commands;


import us.obviously.itmo.prog.manager.Management;

import java.util.HashMap;

public class RemoveKeyCommand extends AbstractCommand {
    public RemoveKeyCommand(Management manager) {
        super(manager, "remove_key", "Удалить элемент из коллекции по его ключу");
        addParameter("key", "Key ъыъ");
    }

    /**
     *
     */
    @Override
    public void execute(HashMap<String, String> args) {

    }
}