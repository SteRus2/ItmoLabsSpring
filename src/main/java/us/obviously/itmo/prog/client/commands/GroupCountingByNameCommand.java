package us.obviously.itmo.prog.client.commands;

import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.console.TablesPrinter;
import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.common.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.exceptions.ServerErrorException;

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
        try {
            var res = this.manager.getDataCollection().groupCountingByName();
            res.forEach((key, list) -> {
                Messages.printStatement("~bl" + key + "~=");
                TablesPrinter.printStudyGroups(list);
                Messages.print("%n%n");
            });
        } catch (BadRequestException e) {
            Messages.printStatement("~reНеверный запрос: " + e.getMessage() + "~=");
        } catch (ServerErrorException e) {
            Messages.printStatement("~Ошибка сервера: " + e.getMessage() + "~=");
        }
    }
}

