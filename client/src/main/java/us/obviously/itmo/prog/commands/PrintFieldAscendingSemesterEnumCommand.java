package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.console.TablesPrinter;
import us.obviously.itmo.prog.manager.Management;
import us.obviously.itmo.prog.server.exceptions.BadRequestException;
import us.obviously.itmo.prog.model.Person;

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
