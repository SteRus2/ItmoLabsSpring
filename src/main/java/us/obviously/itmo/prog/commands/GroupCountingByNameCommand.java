package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.console.TablesPrinter;
import us.obviously.itmo.prog.manager.Management;

import java.util.HashMap;

public class GroupCountingByNameCommand extends AbstractCommand {
    public GroupCountingByNameCommand(Management manager) {
        super(manager, "group_counting_by_name", "Сгруппировать элементы коллекции по значению поля name, вывести количество элементов в каждой группе");
    }


    @Override
    public void execute(HashMap<String, String> args) {
        var res = this.manager.getDataCollection().groupCountingByName();
        res.forEach((key, list) -> {
            Messages.printStatement(ConsoleColors.BLUE + key + ConsoleColors.RESET);
            TablesPrinter.printStudyGroups(list);
            Messages.print("%n%n");
        });
    }
}

