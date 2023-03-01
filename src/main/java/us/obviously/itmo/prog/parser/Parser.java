package us.obviously.itmo.prog.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import us.obviously.itmo.prog.exceptions.FailedToDumpsEx;
import us.obviously.itmo.prog.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.model.StudyGroup;

import java.io.IOException;
import java.util.HashMap;

public abstract class Parser {
    public abstract HashMap<Integer, StudyGroup> loads(String value) throws JsonProcessingException, IncorrectValueException;
    public abstract String dumps(HashMap<Integer, StudyGroup> value) throws FailedToDumpsEx;
}
