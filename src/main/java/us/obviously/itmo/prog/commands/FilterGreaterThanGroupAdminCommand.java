package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.manager.Management;

import java.util.HashMap;

public class FilterGreaterThanGroupAdminCommand extends AbstractCommand {
    public FilterGreaterThanGroupAdminCommand(Management manager) {
        super(manager, "filter_greater_than_group_admin", "Вывести элементы, значение поля groupAdmin которых больше заданного");
        addParameter("groupAdmin", "");
    }

    // groupAdmin

    /**
     * TODO: FILL
     */
    @Override
    public void execute(HashMap<String, String> args) {

    }
}

