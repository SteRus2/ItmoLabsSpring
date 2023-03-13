package us.obviously.itmo.prog.reader;

import com.fasterxml.jackson.core.JsonProcessingException;
import us.obviously.itmo.prog.exceptions.*;
import us.obviously.itmo.prog.model.StudyGroup;
import us.obviously.itmo.prog.parser.Parser;

import java.io.IOException;
import java.util.HashMap;

public abstract class DataReader {
    protected Parser parser;
    public abstract HashMap<Integer, StudyGroup> getData() throws IncorrectValueException, IncorrectValuesTypeException, CantParseDataException, CantFindFileException;
    public abstract void saveData(HashMap<Integer, StudyGroup> data) throws FailedToDumpsEx, CantWriteDataException;
}
