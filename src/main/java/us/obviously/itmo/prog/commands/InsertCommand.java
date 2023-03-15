package us.obviously.itmo.prog.commands;


import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.exceptions.UsedKeyException;
import us.obviously.itmo.prog.forms.StudyGroupForm;
import us.obviously.itmo.prog.manager.Management;
import us.obviously.itmo.prog.model.StudyGroup;

import java.util.HashMap;

public class InsertCommand extends AbstractCommand {
    public InsertCommand(Management manager) {
        super(manager, "insert", "Добавить новый элемент с заданным ключом");
        addParameter("key", "Значение ID");
    }

    /**
     *
     */
    @Override
    public void execute(HashMap<String, String> args) {
        var studyGroupForm = new StudyGroupForm(this.manager);
        var key = args.get("key");
        studyGroupForm.run(key);
        StudyGroup studyGroup = studyGroupForm.build();
        try {
            this.manager.getDataCollection().insertItem(studyGroup, studyGroup.getId());
            Messages.printStatement(ConsoleColors.BLUE +
                    "Новый studyGroup сохранён под id %s".formatted(studyGroup.getId()) +
                    ConsoleColors.RESET);
        } catch (UsedKeyException e) {
            Messages.printStatement(ConsoleColors.RED + "Ошибка при сохранении: " + e.getMessage() + ConsoleColors.RESET);
        }
    }
}