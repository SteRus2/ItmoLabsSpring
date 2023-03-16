package us.obviously.itmo.prog.parser;

import us.obviously.itmo.prog.exceptions.CantParseDataException;
import us.obviously.itmo.prog.exceptions.FailedToDumpsEx;
import us.obviously.itmo.prog.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.exceptions.IncorrectValuesTypeException;
import us.obviously.itmo.prog.model.StudyGroup;

import java.util.HashMap;

/**
 * Класс, позволяющий переводить коллекцию в строку некоторого формата и наоборот
 *
 * @see JsonParser
 * @see XMLParser
 */
public abstract class Parser {
    /**
     * Метод, позволяющий получать коллекцию из строки в некотором формате
     *
     * @param value исходная строка формата
     * @return Возвращает коллекцию с данными
     * @throws IncorrectValueException      Выбросит исключение, если данные в файле не пройдут валидацию
     * @throws IncorrectValuesTypeException Выбросит исключение, если данные в файле будут в неправильном формате
     * @throws CantParseDataException       Выбросит исключение, если строка будет не корректна
     */
    public abstract HashMap<Integer, StudyGroup> loads(String value) throws IncorrectValueException, IncorrectValuesTypeException, CantParseDataException;

    /**
     * Метод, позволяющий получать строку в некотором формате из коллекции
     *
     * @param value Исходная коллекция
     * @return Возвращает строку в некотором формате
     * @throws FailedToDumpsEx Выбросит исключение, если данные не получится перевести в строку
     */
    public abstract String dumps(HashMap<Integer, StudyGroup> value) throws FailedToDumpsEx;
}
