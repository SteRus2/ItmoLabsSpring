package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.manager.Management;

import java.util.HashMap;

public class GroupCountingByNameCommand extends AbstractCommand {
    public GroupCountingByNameCommand(Management manager) {
        super(manager, "group_counting_by_name", "Сгруппировать элементы коллекции по значению поля name, вывести количество элементов в каждой группе");
    }


    /**
     * TODO: FILL
     */
    @Override
    public void execute(HashMap<String, String> args) {

    }
}

