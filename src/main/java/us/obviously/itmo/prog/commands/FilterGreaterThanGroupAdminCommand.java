package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.console.TablesPrinter;
import us.obviously.itmo.prog.exceptions.UsedKeyException;
import us.obviously.itmo.prog.forms.PersonForm;
import us.obviously.itmo.prog.forms.StudyGroupForm;
import us.obviously.itmo.prog.manager.Management;
import us.obviously.itmo.prog.model.Person;
import us.obviously.itmo.prog.model.StudyGroup;

import java.util.HashMap;

public class FilterGreaterThanGroupAdminCommand extends AbstractCommand {
    public FilterGreaterThanGroupAdminCommand(Management manager) {
        super(manager, "filter_greater_than_group_admin", "Вывести элементы, значение поля groupAdmin которых больше заданного");
    }

    /**
     * TODO: FILL
     */
    @Override
    public void execute(HashMap<String, String> args) {

        var personForm = new PersonForm(this.manager);
        personForm.run();
        Person person = personForm.build();
        var res = this.manager.getDataCollection().filterGreaterThanGroupAdmin(person);
        TablesPrinter.printStudyGroups(res);
    }
}

