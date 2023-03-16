package us.obviously.itmo.prog.commands;


import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.exceptions.FormInterruptException;
import us.obviously.itmo.prog.exceptions.IncorrectValueException;
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