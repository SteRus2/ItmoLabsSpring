package us.obviously.itmo.prog.client.commands;

import us.obviously.itmo.prog.client.console.ConsoleColors;
import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.common.exceptions.BadRequestException;

import java.util.HashMap;

/**
 * Команда для удаления из коллекции всех элементов, ключ которых меньше заданного
 */
public class RemoveLowerKeyCommand extends AbstractCommand {
    public RemoveLowerKeyCommand(Management manager) {
        super(manager, "remove_lower_key", "Удалить из коллекции все элементы, ключ которых меньше, чем заданный");
        addParameter("key", "Значение ID");
    }

    /**
     * @inheritDoc
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
        } catch (BadRequestException e) {
            Messages.printStatement("~reОшибка запроса: " + e.getMessage() + "~=");
        }
    }
}