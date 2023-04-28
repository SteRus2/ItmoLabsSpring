package us.obviously.itmo.prog.client.commands;

import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.common.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.exceptions.ServerErrorException;

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
        try {
            this.manager.getDataCollection().clearData();
        } catch (BadRequestException e) {
            Messages.printStatement("~reНеверный запрос: " + e.getMessage() + "~=");
        } catch (ServerErrorException e) {
            Messages.printStatement("~Ошибка сервера: " + e.getMessage() + "~=");
        }
        Messages.printStatement("Коллекция очищена. Воспользуйтесь командой " +
                "~grsave~=" +
                ", чтобы сохранить изменения.");
    }

}