package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.commands.argument_validators.ExistingStudyGroupKeyArgumentValidator;
import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.exceptions.FormInterruptException;
import us.obviously.itmo.prog.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.exceptions.InvalidArgumentException;
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
        try {
            var group = new ExistingStudyGroupKeyArgumentValidator(manager).validate(key);
            studyGroupForm.update(group);
            try {
                StudyGroup studyGroup = studyGroupForm.build();
                try {
                    this.manager.getDataCollection().replaceIfGreater(studyGroup.getId(), studyGroup);
                    Messages.printStatement("~blЕсли это было необходимо, studyGroup обновлён под id %s~=", studyGroup.getId());
                } catch (NoSuchIdException e) {
                    Messages.printStatement(ConsoleColors.RED + "Ошибка при сохранении: " + e.getMessage() + "~=");
                }
            } catch (IncorrectValueException e) {
                Messages.printStatement("~reЧто-то криво заполнили: " + e.getMessage() + "~=");
            }
        } catch (InvalidArgumentException e) {
            Messages.printStatement("~reОшибка с аргументом: " + e.getMessage() + "~=");
        } catch (FormInterruptException e) {
            Messages.printStatement("~blПрервано пользователем.~=");
        }

    }

}
