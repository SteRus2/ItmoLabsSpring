package us.obviously.itmo.prog.client.commands;

import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.manager.Management;

import java.util.HashMap;

/**
 * Команда для очистки коллекции
 */
public class ClearCommand extends AbstractCommand {
    public ClearCommand(Management manager) {
        super(manager, "clear", "Очистить коллекцию");

    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute(HashMap<String, String> args) {
        this.manager.getDataCollection().clearData();
        Messages.printStatement("Коллекция очищена. Воспользуйтесь командой " +
                "~grsave~=" +
                ", чтобы сохранить изменения.");
    }

}