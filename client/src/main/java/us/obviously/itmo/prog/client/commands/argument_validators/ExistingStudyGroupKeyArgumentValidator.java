package us.obviously.itmo.prog.client.commands.argument_validators;

import us.obviously.itmo.prog.common.server.exceptions.InvalidArgumentException;
import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.common.server.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.model.StudyGroup;

/**
 * Проверка, что ключ, введённый пользователь, является id некоторой группы
 */
public class ExistingStudyGroupKeyArgumentValidator extends AbstractArgumentValidator<String, StudyGroup> {

    public ExistingStudyGroupKeyArgumentValidator(Management manager) {
        super(manager);
    }

    /**
     * @inheritDoc
     */
    @Override
    public StudyGroup validate(String value) throws InvalidArgumentException, BadRequestException {
        try {
            Integer valueInt = Integer.parseInt(value);
            var group = this.manager.getDataCollection().checkGroup(valueInt);
            if (group == null) {
                throw new InvalidArgumentException("Группа с ключом " + valueInt + " не существует.");
            }
            return group;
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Неверный формат ключа. Ключ должен быть целым числом от -1000000 до 1000000.");
        }
    }
}
