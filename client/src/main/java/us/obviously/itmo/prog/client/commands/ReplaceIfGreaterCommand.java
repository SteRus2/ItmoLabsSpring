package us.obviously.itmo.prog.client.commands;

import us.obviously.itmo.prog.client.commands.argument_validators.ExistingStudyGroupKeyArgumentValidator;
import us.obviously.itmo.prog.common.console.ConsoleColors;
import us.obviously.itmo.prog.common.console.Messages;
import us.obviously.itmo.prog.common.server.exceptions.FormInterruptException;
import us.obviously.itmo.prog.common.server.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.common.server.exceptions.InvalidArgumentException;
import us.obviously.itmo.prog.client.forms.StudyGroupForm;
import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.common.server.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.common.server.exceptions.NoSuchIdException;

import java.util.HashMap;

/**
 * Команда для обновления по id, но только если новое значение больше старого
 */
public class ReplaceIfGreaterCommand extends AbstractCommand {
    public ReplaceIfGreaterCommand(Management manager) {
        super(manager, "replace_if_greater", "Заменить значение по ключу, если новое значение больше старого");
        addParameter("key", "Значение ID");

    }

    /**
     * @inheritDoc
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
                    this.manager.getDataCollection().replaceIfGreater(studyGroup, studyGroup.getId());
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
        } catch (BadRequestException e) {
            Messages.printStatement("~reОшибка запроса: " + e.getMessage() + "~=");
        }

    }

}
