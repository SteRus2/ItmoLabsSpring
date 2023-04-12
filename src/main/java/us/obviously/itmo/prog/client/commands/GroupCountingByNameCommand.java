package us.obviously.itmo.prog.client.commands;

import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.console.TablesPrinter;
import us.obviously.itmo.prog.client.manager.Management;

import java.util.HashMap;

/**
 * Команда для группировки элементов коллекции по полю name, выводит количество элементов в каждой группе
 */
public class GroupCountingByNameCommand extends AbstractCommand {
    public GroupCountingByNameCommand(Management manager) {
        super(manager, "group_counting_by_name", "Сгруппировать элементы коллекции по значению поля name, вывести количество элементов в каждой группе");
    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute(HashMap<String, String> args) {
        var res = this.manager.getDataCollection().groupCountingByName();
        res.forEach((key, list) -> {
            Messages.printStatement("~bl" + key + "~=");
            TablesPrinter.printStudyGroups(list);
            Messages.print("%n%n");
        });
    }
}

