package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.manager.Management;
import us.obviously.itmo.prog.server.exceptions.BadRequestException;

import java.util.HashMap;

/**
 * Команда для сохранения текущей коллекции в файл
 */
public class SaveCommand extends AbstractCommand {
    public SaveCommand(Management manager) {
        super(manager, "save", "Сохранить коллекцию в файл");
    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute(HashMap<String, String> args) {
        try {
            this.manager.getDataCollection().saveData();
            Messages.printStatement(ConsoleColors.GREEN + "Успешно сохранено!~=");
        } catch (BadRequestException e) {
            Messages.printStatement("~reОшибка запроса: " + e.getMessage() + "~=");
        }
    }
}