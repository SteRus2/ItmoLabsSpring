package us.obviously.itmo.prog.commands;


import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.exceptions.NoSuchIdException;
import us.obviously.itmo.prog.manager.Management;

import java.util.HashMap;

public class RemoveKeyCommand extends AbstractCommand {
    public RemoveKeyCommand(Management manager) {
        super(manager, "remove_key", "Удалить элемент из коллекции по его ключу");
        addParameter("key", "Значение ID");
    }

    /**
     *
     */
    @Override
    public void execute(HashMap<String, String> args) {
        String key = args.get("key");
        try {
            int intKey = Integer.parseInt(key);
            this.manager.getDataCollection().removeItem(intKey);
            System.out.printf("Элемент %s успешно удалён.%n", key);
        } catch (NumberFormatException e) {
            System.out.printf("Ключ должен быть представлен натуральным числом.%n");
        } catch (NoSuchIdException e) {
            System.out.println(ConsoleColors.RED + e + ConsoleColors.RESET);
        }
    }
}