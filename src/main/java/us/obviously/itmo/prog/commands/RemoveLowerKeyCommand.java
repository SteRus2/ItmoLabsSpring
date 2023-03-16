package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.manager.Management;

import java.util.HashMap;

public class RemoveLowerKeyCommand extends AbstractCommand {
    public RemoveLowerKeyCommand(Management manager) {
        super(manager, "remove_lower_key", "Удалить из коллекции все элементы, ключ которых меньше, чем заданный");
        addParameter("key", "Значение ID");
    }

    /**
     * TODO: FILL
     */
    @Override
    public void execute(HashMap<String, String> args) {
        var rawKey = args.get("key");
        try {
            var key = Integer.parseInt(rawKey);
            this.manager.getDataCollection().removeLowerKey(key);
            Messages.printStatement("Все элементы с ключом меньшим, чем %s, были удалены. ".formatted(key) +
                    "Введите " + ConsoleColors.GREEN + "show~=" + ", чтобы просмотреть. \n" +
                    "Введите " + ConsoleColors.GREEN + "save~=" + ", чтобы сохранить изменения.");
        } catch (NumberFormatException e) {
            Messages.printStatement("Не получается чето");
        }
    }
}