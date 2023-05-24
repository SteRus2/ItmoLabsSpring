package us.obviously.itmo.prog.client.commands;


import us.obviously.itmo.prog.client.commands.argument_validators.ExistingStudyGroupKeyArgumentValidator;
import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.exceptions.FormInterruptException;
import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.client.exceptions.InvalidArgumentException;
import us.obviously.itmo.prog.client.forms.StudyGroupForm;
import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.common.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.exceptions.ServerErrorException;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.server.exceptions.NoSuchIdException;

import java.util.HashMap;

/**
 * Команда обновления группы по id
 */
public class UpdateCommand extends AbstractCommand {
    public UpdateCommand(Management manager) {
        super(manager, "update", "обновить значение элемента коллекции, id которого равен заданному");
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
            StudyGroup studyGroup = studyGroupForm.build();
            this.manager.getDataCollection().updateItem(studyGroup, Integer.parseInt(key));
            Messages.printStatement("~blstudyGroup обновлён под id %s~=", studyGroup.getId());
        } catch (NoSuchIdException e) {
            Messages.printStatement("~reОшибка при сохранении: " + e.getMessage() + "~=");
        } catch (BadRequestException e) {
            Messages.printStatement("~reОшибка запроса: " + e.getMessage() + "~=");
        } catch (ServerErrorException e) {
            Messages.printStatement("~Ошибка сервера: " + e.getMessage() + "~=");
        } catch (IncorrectValueException e) {
            Messages.printStatement("~reЧто-то криво заполнили: " + e.getMessage() + "~=");
        } catch (InvalidArgumentException e) {
            Messages.printStatement("~reОшибка с аргументом: " + e.getMessage() + "~=");
        } catch (FormInterruptException e) {
            Messages.printStatement("~blПрервано пользователем.~=");
        }
    }
}