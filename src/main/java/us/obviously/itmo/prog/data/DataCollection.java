package us.obviously.itmo.prog.data;

import us.obviously.itmo.prog.exceptions.*;
import us.obviously.itmo.prog.model.Person;
import us.obviously.itmo.prog.model.Semester;
import us.obviously.itmo.prog.model.StudyGroup;
import us.obviously.itmo.prog.reader.DataReader;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Класс, предоставляющий основной функционал по работе с коллекцией данных
 */
public class DataCollection {
    private final DataReader dataReader;
    private HashMap<Integer, StudyGroup> data;
    private final String type;
    private final Date initDate;

    /**
     * Конструктор, с помощью которого происходит инициализация коллекции, коллекцию получаем из dataReader
     *
     * @param dataReader Источник данных
     * @throws IncorrectValueException      Выбросит исключение, если данные не пройдут валидацию
     * @throws IncorrectValuesTypeException Выбросит исключение, если данные будут в неправильном формате
     * @throws CantParseDataException       Выбросит исключение, если не сможет перевести дынные в нужный формат
     * @throws CantFindFileException        Выбросит исключение, если не сможет найти путь до данных
     */
    public DataCollection(DataReader dataReader) throws IncorrectValueException, IncorrectValuesTypeException, CantParseDataException, CantFindFileException, FileNotReadableException {
        this.dataReader = dataReader;
        this.data = dataReader.getData();
        type = StudyGroup.class.getName();
        initDate = new Date();
    }

    /**
     * Метод, для получения информации о коллекции
     *
     * @return Информацию о коллекцию в формате объекта
     * @see DataInfo
     */
    public DataInfo getInfo() {
        int count = data.size();
        return new DataInfo(type, initDate, count);
    }

    /**
     * Возвращает Коллекцию
     *
     * @return Коллекция
     */
    public HashMap<Integer, StudyGroup> getData() {
        return data;
    }

    /**
     * Метод, позволяющий добавить в коллекцию новую запись с новым ключом
     *
     * @param item Новый элемент коллекции
     * @param key  Ключ элемента
     * @throws UsedKeyException Выбросит исключение, если ключ уже используется
     */
    public void insertItem(StudyGroup item, int key) throws UsedKeyException {
        if (data.containsKey(key)) {
            throw new UsedKeyException("К сожалению, ключ уже используется");
        } else {
            data.put(key, item);
        }
    }

    /**
     * Позволяет обновить объект в коллекции по заданному ключу
     *
     * @param item Новый элемент
     * @param key  Ключ элемента, который нужно обновить
     * @throws NoSuchIdException Выбросит исключение, если подобного ключа в коллекции нет
     */
    public void updateItem(StudyGroup item, int key) throws NoSuchIdException {
        if (!data.containsKey(key)) {
            throw new NoSuchIdException("Объекта с таким id нет в коллекции");
        }
        item.setCreationDate(data.get(key).getCreationDate());
        data.put(key, item);
    }

    /**
     * Метод позволяющий убрать элемент из коллекции по заданному ключу
     *
     * @param key Ключ объекта
     * @throws NoSuchIdException Выбросит исключение, если элемента под таким ключом нет
     *                           <br>(ну, к слову, семантика этого метода такова - по итогу в коллекции не должно быть элемента с ключом key,
     *                           <br>в целом, если его там и так не было, то зачем выбрасывать исключение? Элемента в коллекции уже нет, разработчики решили, что надо)
     */
    public void removeItem(int key) throws NoSuchIdException {
        if (!data.containsKey(key)) {
            throw new NoSuchIdException("Объекта с таким id нет в коллекции");
        }
        data.remove(key);
    }

    /**
     * Метод, позволяющий полностью очистить коллекцию
     */
    public void clearData() {
        data.clear();
    }

    /**
     * Метод, позволяющий сохранить коллекцию
     *
     * @throws FailedToDumpsEx        Выбросит исключение, если не сможет перевести коллекцию в нужный формат
     * @throws CantWriteDataException Выбросит исключение, если не сможет записать данные
     */
    public void saveData() throws FailedToDumpsEx, CantWriteDataException, FileNotWritableException {
        dataReader.saveData(data);
    }

    /**
     * Метод, позволяющий заменить объект по заданному ключу новым объектом, если новый объект больше старого
     *
     * @param key  Ключ объекта
     * @param item Новый объект
     * @throws NoSuchIdException Выбросит исключение, если объекта с данным ключом нет в коллекции
     */
    public void replaceIfGreater(int key, StudyGroup item) throws NoSuchIdException {
        if (!data.containsKey(key)) {
            throw new NoSuchIdException("Объекта с таким id нет в коллекции");
        }
        if (data.get(key).compareTo(item) > 0) {
            data.put(key, item);
        }
    }

    /**
     * Метод, позволяющий удалить из коллекции все элементы, ключ которых больше заданного
     *
     * @param key Ключ
     */
    public void removeGreaterKey(int key) {
        data = data.keySet()
                .stream()
                .filter(x -> x <= key)
                .collect(Collectors.toMap(Function.identity(), data::get, (prev, next) -> next, HashMap::new));
    }

    /**
     * Метод, позволяющий удалить из коллекции все элементы, ключ которых меньше заданного
     *
     * @param key Ключ
     */
    public void removeLowerKey(int key) {
        data  = data.keySet()
                .stream()
                .filter(x -> x >= key)
                .collect(Collectors.toMap(Function.identity(), data::get, (prev, next) -> next, HashMap::new));
    }

    /**
     * Метод, который позволяет сгруппировать элементы коллекции по значению поля name
     *
     * @return Возвращает HashMap с ключом в виде имени и значением - List со всеми элементами с данным именем
     */
    public Map<String, List<StudyGroup>> groupCountingByName() {
        return data.values()
                .stream()
                .collect(Collectors.groupingBy(StudyGroup::getName));
    }

    /**
     * Метод, который позволяет получить все элементы, поле groupAdmin которых больше чем данный
     *
     * @param groupAdmin groupAdmin, по которому происходит проверка
     * @return List, состоящий из всех элементов поле groupAdmin которых больше чем данный
     */
    public List<StudyGroup> filterGreaterThanGroupAdmin(Person groupAdmin) {
        return data.values()
                .stream()
                .filter(x -> x.getGroupAdmin().compareTo(groupAdmin) < 0)
                .collect(Collectors.toList());
    }

    /**
     * Метод, позволяющий получить значения поля semesterEnum все элементов в порядке возрастания
     *
     * @return List из элементов типа Semester
     */
    public List<Semester> printFieldAscendingSemesterEnum() {
        return data.values()
                .stream()
                .map(StudyGroup::getSemesterEnum)
                .sorted()
                .collect(Collectors.toList());
    }

    public boolean canSaveData(){
        return dataReader.canSaveData();
    }
}
