package us.obviously.itmo.prog.client.commands;

import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.manager.Management;

import java.util.HashMap;

/**
 * Команда для вывода информации по группе
 */
public class InfoCommand extends AbstractCommand {

    public InfoCommand(Management manager) {
        super(manager, "info", "Вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute(HashMap<String, String> args) {
        var info = this.manager.getDataCollection().getInfo();
        Messages.printStatement("Количество: ~bl" + info.getCount() + "~=");
        Messages.printStatement("       Тип: ~bl" + info.getType() + "~=");
        Messages.printStatement("      Дата: ~bl" + info.getDate() + "~=");
    }
}
