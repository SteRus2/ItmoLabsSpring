package us.obviously.itmo.prog.commands;


import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.server.exceptions.FormInterruptException;
import us.obviously.itmo.prog.server.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.forms.StudyGroupForm;
import us.obviously.itmo.prog.manager.Management;
import us.obviously.itmo.prog.server.exceptions.BadRequestException;
import us.obviously.itmo.prog.model.StudyGroup;
import us.obviously.itmo.prog.server.exceptions.UsedKeyException;

import java.util.HashMap;

/**
 * Команда для добавления нового элемента с заданным ключом
 */
public class InsertCommand extends AbstractCommand {
    public InsertCommand(Management manager) {
        super(manager, "insert", "Добавить новый элемент с заданным ключом");
        //addParameter("key", "Значение ID");
    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute(HashMap<String, String> args) {
        var studyGroupForm = new StudyGroupForm(this.manager);
        //var key = args.get("key");
        try {
            manager.getDataCollection().ping();
            studyGroupForm.create(String.valueOf(1));
            StudyGroup studyGroup = studyGroupForm.build();
            try {
                var newId = this.manager.getDataCollection().insertItem(studyGroup, studyGroup.getId());
                if (newId.equals(-1)) {
                    throw new UsedKeyException("Невозможно сохранить объект");
                }
                Messages.printStatement("~blНовый studyGroup сохранён под id %s~=", newId);
            } catch (UsedKeyException e) {
                Messages.printStatement("~reОшибка при сохранении: " + e.getMessage() + "~=");
            }
        } catch (IncorrectValueException e) {
            Messages.printStatement("~reЧто-то криво заполнили: " + e.getMessage() + "~=");
        } catch (FormInterruptException e) {
            Messages.printStatement("~blПрервано пользователем.~=");
        } catch (BadRequestException e) {
            Messages.printStatement("~reОшибка запроса: " + e.getMessage() + "~=");
        }
    }
}