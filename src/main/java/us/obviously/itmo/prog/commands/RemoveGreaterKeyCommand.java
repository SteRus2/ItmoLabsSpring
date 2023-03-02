package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.manager.Management;

import java.util.HashMap;

public class RemoveGreaterKeyCommand extends AbstractCommand {
    public RemoveGreaterKeyCommand(Management manager) {
        super(manager, "remove_greater_key", "Удалить из коллекции все элементы, ключ которых превышает заданный");
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
            this.manager.getDataCollection().removeGreaterKey(key);
            System.out.println("Все элементы с ключом большим, чем %s, были удалены. ".formatted(key) +
                            "Введите " + ConsoleColors.GREEN + "show" + ConsoleColors.RESET + ", чтобы просмотреть. \n" +
                    "Введите " + ConsoleColors.GREEN + "save" + ConsoleColors.RESET + ", чтобы сохранить изменения.");
        } catch (NumberFormatException e) {
            System.out.println("Не получается чето");
        }
    }
}


