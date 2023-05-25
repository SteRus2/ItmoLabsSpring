package us.obviously.itmo.prog.client.commands;

import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.console.TablesPrinter;
import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.common.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.model.Person;

import java.util.HashMap;

/**
 * Команда для вывода значения поли semesterEnum для всех элементов в порядке возрастания
 *
 * @see Person
 */
public class PrintFieldAscendingSemesterEnumCommand extends AbstractCommand {
    public PrintFieldAscendingSemesterEnumCommand(Management manager) {
        super(manager, "print_field_ascending_semester_enum", "Вывести значения поля semesterEnum всех элементов в порядке возрастания");
    }


    /**
     * @inheritDoc
     */
    @Override
    public void execute(HashMap<String, String> args) {
        try {
            TablesPrinter.printSemesters(this.manager.getDataCollection().printFieldAscendingSemesterEnum());
        } catch (BadRequestException e) {
            Messages.printStatement("~reОшибка запроса: " + e.getMessage() + "~=");
        }
    }
}
