package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.console.TablesPrinter;
import us.obviously.itmo.prog.manager.Management;

import java.util.HashMap;

public class PrintFieldAscendingSemesterEnumCommand extends AbstractCommand {
    public PrintFieldAscendingSemesterEnumCommand(Management manager) {
        super(manager, "print_field_ascending_semester_enum", "Вывести значения поля semesterEnum всех элементов в порядке возрастания");
    }


    /**
     * TODO: FILL
     */
    @Override
    public void execute(HashMap<String, String> args) {
        TablesPrinter.printSemesters(this.manager.getDataCollection().printFieldAscendingSemesterEnum());
    }
}
