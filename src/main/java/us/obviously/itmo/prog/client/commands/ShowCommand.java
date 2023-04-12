package us.obviously.itmo.prog.client.commands;

import us.obviously.itmo.prog.client.console.TablesPrinter;
import us.obviously.itmo.prog.client.manager.Management;

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
        var hs = this.manager.getDataCollection().getData();
        TablesPrinter.printStudyGroups(hs);
    }
}
