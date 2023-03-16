package us.obviously.itmo.prog.commands.argument_validators;

import us.obviously.itmo.prog.exceptions.InvalidArgumentException;
import us.obviously.itmo.prog.manager.Management;
import us.obviously.itmo.prog.model.StudyGroup;

public class ExistingStudyGroupKeyArgumentValidator extends AbstractArgumentValidator<String, StudyGroup> {

    public ExistingStudyGroupKeyArgumentValidator(Management manager) {
        super(manager);
    }

    @Override
    public StudyGroup validate(String value) throws InvalidArgumentException {
        try {
            Integer valueInt = Integer.parseInt(value);
            var group = this.manager.getDataCollection().getData().get(valueInt);
            if (group == null) {
                throw new InvalidArgumentException("Группа с ключом " + valueInt + " не существует.");
            }
            return group;
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Неверный формат ключа. Ключ должен быть целым числом от -1000000 до 1000000.");
        }
    }
}
