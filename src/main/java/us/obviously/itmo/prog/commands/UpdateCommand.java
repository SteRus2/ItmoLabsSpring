package us.obviously.itmo.prog.commands;


import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.exceptions.NoSuchIdException;
import us.obviously.itmo.prog.exceptions.UsedKeyException;
import us.obviously.itmo.prog.forms.IntegerFormField;
import us.obviously.itmo.prog.forms.StudyGroupForm;
import us.obviously.itmo.prog.manager.Management;
import us.obviously.itmo.prog.model.StudyGroup;

import java.util.HashMap;

public class UpdateCommand extends AbstractCommand {
    public UpdateCommand(Management manager) {
        super(manager, "update", "обновить значение элемента коллекции, id которого равен заданному");
        addParameter("key", "Значение ID");
    }

    /**
     *
     */
    @Override
    public void execute(HashMap<String, String> args) {
        var studyGroupForm = new StudyGroupForm(this.manager);
        var key = args.get("key");
        studyGroupForm.update(key);
        StudyGroup studyGroup = studyGroupForm.build();
        try {
            this.manager.getDataCollection().updateItem(studyGroup, studyGroup.getId());
            Messages.printStatement(ConsoleColors.BLUE +
                    "studyGroup обновлён под id %s".formatted(studyGroup.getId()) +
                    ConsoleColors.RESET);
        } catch (NoSuchIdException e) {
            Messages.printStatement(ConsoleColors.RED + "Ошибка при сохранении: " + e.getMessage() + ConsoleColors.RESET);
        }
    }
}