package us.obviously.itmo.prog.client.commands;


import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.exceptions.FormInterruptException;
import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.server.exceptions.UsedKeyException;
import us.obviously.itmo.prog.client.forms.StudyGroupForm;
import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.common.model.StudyGroup;

import java.util.HashMap;

/**
 * Команда для добавления нового элемента с заданным ключом
 */
public class InsertCommand extends AbstractCommand {
    public InsertCommand(Management manager) {
        super(manager, "insert", "Добавить новый элемент с заданным ключом");
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
            studyGroupForm.create(key);
            StudyGroup studyGroup = studyGroupForm.build();
            try {
                this.manager.getDataCollection().insertItem(studyGroup, studyGroup.getId());
                Messages.printStatement("~blНовый studyGroup сохранён под id %s~=", studyGroup.getId());
            } catch (UsedKeyException e) {
                Messages.printStatement("~reОшибка при сохранении: " + e.getMessage() + "~=");
            }
        } catch (IncorrectValueException e) {
            Messages.printStatement("~reЧто-то криво заполнили: " + e.getMessage() + "~=");
        } catch (FormInterruptException e) {
            Messages.printStatement("~blПрервано пользователем.~=");
        }
    }
}