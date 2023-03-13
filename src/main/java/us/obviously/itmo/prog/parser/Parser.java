package us.obviously.itmo.prog.parser;

import us.obviously.itmo.prog.exceptions.CantParseDataException;
import us.obviously.itmo.prog.exceptions.FailedToDumpsEx;
import us.obviously.itmo.prog.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.exceptions.IncorrectValuesTypeException;
import us.obviously.itmo.prog.model.StudyGroup;

import java.util.HashMap;

public abstract class Parser {
    public abstract HashMap<Integer, StudyGroup> loads(String value) throws IncorrectValueException, IncorrectValuesTypeException, CantParseDataException;
    public abstract String dumps(HashMap<Integer, StudyGroup> value) throws FailedToDumpsEx;
}
