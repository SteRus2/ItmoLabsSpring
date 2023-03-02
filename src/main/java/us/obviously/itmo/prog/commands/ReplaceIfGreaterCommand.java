package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.exceptions.NoSuchIdException;
import us.obviously.itmo.prog.forms.StudyGroupForm;
import us.obviously.itmo.prog.manager.Management;
import us.obviously.itmo.prog.model.StudyGroup;

import java.util.HashMap;

public class ReplaceIfGreaterCommand extends AbstractCommand {
    public ReplaceIfGreaterCommand(Management manager) {
        super(manager, "replace_if_greater", "Заменить значение по ключу, если новое значение больше старого");
        addParameter("key", "Значение ID");

    }

    /**
     * TODO: FILL
     */
    @Override
    public void execute(HashMap<String, String> args) {
        var studyGroupForm = new StudyGroupForm(this.manager);
        var key = args.get("key");
        studyGroupForm.update(key);
        StudyGroup studyGroup = studyGroupForm.build();
        try {
            this.manager.getDataCollection().replaceIfGreater(studyGroup.getId(), studyGroup);
            System.out.println(ConsoleColors.BLUE +
                    "Если это было необходимо, studyGroup обновлён под id %s".formatted(studyGroup.getId()) +
                    ConsoleColors.RESET);
        } catch (NoSuchIdException e) {
            System.out.println(ConsoleColors.RED + "Ошибка при сохранении: " + e.getMessage() + ConsoleColors.RESET);
        }
    }

}
