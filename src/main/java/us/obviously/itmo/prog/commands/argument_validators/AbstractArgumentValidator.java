package us.obviously.itmo.prog.commands.argument_validators;

import us.obviously.itmo.prog.exceptions.InvalidArgumentException;
import us.obviously.itmo.prog.manager.Management;

abstract class AbstractArgumentValidator<T, U> {
    Management manager;
    AbstractArgumentValidator(Management manager) {
        this.manager = manager;
    }
    abstract public U validate(T value) throws InvalidArgumentException;
}
