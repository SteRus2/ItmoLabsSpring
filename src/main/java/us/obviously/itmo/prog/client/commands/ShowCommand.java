package us.obviously.itmo.prog.client.commands;

import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.console.TablesPrinter;
import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.common.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.model.StudyGroup;

import java.util.HashMap;

/**
 * Команда для показа списка всех групп
 */
public class ShowCommand extends AbstractCommand {
    public ShowCommand(Management manager) {
        super(manager, "show", "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute(HashMap<String, String> args) {
        HashMap<Integer, StudyGroup> hs = null;
        try {
            hs = this.manager.getDataCollection().getData();
            TablesPrinter.printStudyGroups(hs);

        } catch (BadRequestException e) {
            Messages.printStatement("~reОшибка запроса: " + e.getMessage() + "~=");
        }
    }
}
