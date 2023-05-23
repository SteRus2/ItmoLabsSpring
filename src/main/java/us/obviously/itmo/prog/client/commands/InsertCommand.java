package us.obviously.itmo.prog.client.commands;


import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.exceptions.FormInterruptException;
import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.client.forms.StudyGroupForm;
import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.common.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.exceptions.ServerErrorException;
import us.obviously.itmo.prog.common.model.StudyGroup;
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
            studyGroupForm.create(String.valueOf(1));
            StudyGroup studyGroup = studyGroupForm.build();
            try {
                var newId = this.manager.getDataCollection().insertItem(studyGroup, studyGroup.getId());
                if (newId.equals(-1)){
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
        } catch (ServerErrorException e) {
            Messages.printStatement("~Ошибка сервера: " + e.getMessage() + "~=");
        }
    }
}