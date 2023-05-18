package us.obviously.itmo.prog.client.commands;

import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.console.TablesPrinter;
import us.obviously.itmo.prog.client.exceptions.FormInterruptException;
import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.client.forms.PersonForm;
import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.common.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.exceptions.ServerErrorException;
import us.obviously.itmo.prog.common.model.Person;

import java.util.HashMap;

/**
 * Команда для вывода всех элементов, значения поля groupAdmin которых больше заданного
 */
public class FilterGreaterThanGroupAdminCommand extends AbstractCommand {
    public FilterGreaterThanGroupAdminCommand(Management manager) {
        super(manager, "filter_greater_than_group_admin", "Вывести элементы, значение поля groupAdmin которых больше заданного");
    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute(HashMap<String, String> args) {

        var personForm = new PersonForm(this.manager);
        try {
            personForm.create();
            Person person = personForm.build();
            var res = this.manager.getDataCollection().filterGreaterThanGroupAdmin(person);
            TablesPrinter.printStudyGroups(res);
        } catch (IncorrectValueException e) {
            Messages.printStatement("~reЧто-то криво заполнили: " + e.getMessage() + "~=");
        } catch (FormInterruptException e) {
            Messages.printStatement("~blПрервано пользователем.~=");
        } catch (BadRequestException e) {
            Messages.printStatement("~reОшибка запроса: " + e.getMessage() + "~=");
        } catch (ServerErrorException e) {
            Messages.printStatement("~Ошибка сервера: " + e.getMessage() + "~=");
        }
    }
}

