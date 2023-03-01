package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.manager.Management;

import java.util.HashMap;

public class RemoveGreaterKeyCommand extends AbstractCommand {
    public RemoveGreaterKeyCommand(Management manager) {
        super(manager, "remove_greater_key", "Удалить из коллекции все элементы, ключ которых превышает заданный");
        addParameter("key", "Key ъыъ");
    }
    // null

    /**
     * TODO: FILL
     */
    @Override
    public void execute(HashMap<String, String> args) {

    }
}


