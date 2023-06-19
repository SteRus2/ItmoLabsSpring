package us.obviously.itmo.prog.common.server.reader;

import us.obviously.itmo.prog.common.server.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.common.server.exceptions.*;
import us.obviously.itmo.prog.common.server.parser.Parser;

import java.util.HashMap;

/**
 * Класс, позволяющий получать данные в некотором представлении и переводить их в готовую коллекцию
 *
 * @see FileReader
 * @see FileFormatReader
 */
@Deprecated
public abstract class DataReader {
    protected Parser parser;

    /**
     * Метод, который считывает данные и переводит их в коллекцию
     *
     * @return Возвращает коллекцию
     * @throws IncorrectValueException      Выбросит исключение, если данные не пройдут валидацию
     * @throws IncorrectValuesTypeException Выбросит исключение, если данные будут в неправильном формате
     * @throws CantParseDataException       Выбросит исключение, если не сможет перевести дынные в нужный формат
     * @throws CantFindFileException        Выбросит исключение, если не сможет найти путь до данных
     */
    public abstract HashMap<Integer, StudyGroup> getData() throws IncorrectValueException, IncorrectValuesTypeException, CantParseDataException, CantFindFileException, FileNotReadableException;

    /**
     * Метод, позволяющий сохранять коллекцию
     *
     * @param data Исходная коллекция
     * @throws FailedToDumpsEx        Выбросит исключение, если не сможет перевести коллекцию в нужный формат
     * @throws CantWriteDataException Выбросит исключение, если не сможет записать данные
     */
    public abstract void saveData(HashMap<Integer, StudyGroup> data) throws FailedToDumpsEx, CantWriteDataException, FileNotWritableException;

    public abstract boolean canSaveData();
}
