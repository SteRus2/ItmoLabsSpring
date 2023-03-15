package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.manager.Management;

import java.util.HashMap;

public class ClearCommand extends AbstractCommand {
    public ClearCommand(Management manager) {
        super(manager, "clear", "Очистить коллекцию");

    }

    /**
     * TODO: FILL
     */
    @Override
    public void execute(HashMap<String, String> args) {
        this.manager.getDataCollection().clearData();
        Messages.printStatement("Коллекция очищена. Воспользуйтесь командой " +
                ConsoleColors.GREEN + "save" + ConsoleColors.RESET +
                ", чтобы сохранить изменения.");
    }

}