package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.exceptions.CantWriteDataException;
import us.obviously.itmo.prog.exceptions.FailedToDumpsEx;
import us.obviously.itmo.prog.exceptions.FileNotWritableException;
import us.obviously.itmo.prog.manager.Management;

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
        } catch (FailedToDumpsEx | CantWriteDataException | FileNotWritableException e) {
            Messages.printStatement("~reОшибка при сохранении: " + e.getMessage() + "~=");
        }
    }
}