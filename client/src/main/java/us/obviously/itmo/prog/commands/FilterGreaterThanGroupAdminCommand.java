package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.console.TablesPrinter;
import us.obviously.itmo.prog.server.exceptions.FormInterruptException;
import us.obviously.itmo.prog.server.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.forms.PersonForm;
import us.obviously.itmo.prog.manager.Management;
import us.obviously.itmo.prog.server.exceptions.BadRequestException;
import us.obviously.itmo.prog.model.Person;

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
        }
    }
}

