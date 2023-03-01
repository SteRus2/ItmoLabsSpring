package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.manager.Management;

import java.util.HashMap;

public class RemoveLowerKeyCommand extends AbstractCommand {
    public RemoveLowerKeyCommand(Management manager) {
        super(manager, "remove_lower_key", "Удалить из коллекции все элементы, ключ которых меньше, чем заданный");
        addParameter("key", "Key ъыъ");
    }

    /**
     * TODO: FILL
     */
    @Override
    public void execute(HashMap<String, String> args) {

    }
}