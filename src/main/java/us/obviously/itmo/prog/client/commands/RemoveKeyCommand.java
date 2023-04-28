package us.obviously.itmo.prog.client.commands;


import us.obviously.itmo.prog.client.console.ConsoleColors;
import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.common.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.exceptions.ServerErrorException;
import us.obviously.itmo.prog.server.exceptions.NoSuchIdException;

import java.util.HashMap;

/**
 * Команда для удаления элемента из коллекции по ключу
 */
public class RemoveKeyCommand extends AbstractCommand {
    public RemoveKeyCommand(Management manager) {
        super(manager, "remove_key", "Удалить элемент из коллекции по его ключу");
        addParameter("key", "Значение ID");
    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute(HashMap<String, String> args) {
        String key = args.get("key");
        try {
            int intKey = Integer.parseInt(key);
            this.manager.getDataCollection().removeItem(intKey);
            Messages.print("Элемент %s успешно удалён.%n", key);
        } catch (NumberFormatException e) {
            Messages.print("Ключ должен быть представлен натуральным числом.%n");
        } catch (NoSuchIdException e) {
            Messages.printStatement(ConsoleColors.RED + e.getMessage() + "~=");
        } catch (BadRequestException e) {
            Messages.printStatement("~reНеверный запрос: " + e.getMessage() + "~=");
        } catch (ServerErrorException e) {
            Messages.printStatement("~Ошибка сервера: " + e.getMessage() + "~=");
        }
    }
}