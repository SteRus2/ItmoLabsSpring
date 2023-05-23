package us.obviously.itmo.prog.server.data;

import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.common.data.DataInfo;
import us.obviously.itmo.prog.common.data.LocalDataCollection;
import us.obviously.itmo.prog.common.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.model.Person;
import us.obviously.itmo.prog.common.model.Semester;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.server.exceptions.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Класс, предоставляющий основной функционал по работе с коллекцией данных
 */
public class DataStorage implements LocalDataCollection {
    private HashMap<Integer, StudyGroup> data;
    private final String type;
    private final Date initDate;

    /**
     * Конструктор, с помощью которого происходит инициализация коллекции, коллекцию получаем из dataReader
     *
     * @param initData Источник данных
     * @throws IncorrectValueException      Выбросит исключение, если данные не пройдут валидацию
     * @throws IncorrectValuesTypeException Выбросит исключение, если данные будут в неправильном формате
     * @throws CantParseDataException       Выбросит исключение, если не сможет перевести дынные в нужный формат
     * @throws CantFindFileException        Выбросит исключение, если не сможет найти путь до данных
     */
    public DataStorage(HashMap<Integer, StudyGroup> initData) throws IncorrectValueException, IncorrectValuesTypeException, CantParseDataException, CantFindFileException, FileNotReadableException {
        this.data = initData;
        type = StudyGroup.class.getName();
        initDate = new Date();
    }

    /**
     * Метод, для получения информации о коллекции
     *
     * @return Информацию о коллекцию в формате объекта
     * @see DataInfo
     */
    @Override
    public DataInfo getInfo() {
        int count = data.size();
        return new DataInfo(type, initDate, count);
    }

    /**
     * Возвращает Коллекцию
     *
     * @return Коллекция
     */
    @Override
    public HashMap<Integer, StudyGroup> getData() {
        return data;
    }

    /**
     * Метод, позволяющий добавить в коллекцию новую запись с новым ключом
     *
     * @param item Новый элемент коллекции
     * @param key  Ключ элемента
     * @return
     * @throws UsedKeyException Выбросит исключение, если ключ уже используется
     */
    @Override
    public Integer insertItem(StudyGroup item, int key) throws UsedKeyException {
        if (data.containsKey(key)) {
            throw new UsedKeyException("К сожалению, ключ уже используется");
        } else {
            data.put(key, item);
        }
        return 0;
    }

    /**
     * Позволяет обновить объект в коллекции по заданному ключу
     *
     * @param item Новый элемент
     * @param key  Ключ элемента, который нужно обновить
     * @throws NoSuchIdException Выбросит исключение, если подобного ключа в коллекции нет
     */
    @Override
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
    @Override
    public void removeItem(int key) throws NoSuchIdException {
        if (!data.containsKey(key)) {
            throw new NoSuchIdException("Объекта с таким id нет в коллекции");
        }
        data.remove(key);
    }

    /**
     * Метод, позволяющий полностью очистить коллекцию
     */
    @Override
    public void clearData() {
        data.clear();
    }

    @Override
    public void saveData() throws FailedToDumpsEx, CantWriteDataException, FileNotWritableException {

    }


    /**
     * Метод, позволяющий заменить объект по заданному ключу новым объектом, если новый объект больше старого
     *
     * @param key  Ключ объекта
     * @param item Новый объект
     * @throws NoSuchIdException Выбросит исключение, если объекта с данным ключом нет в коллекции
     */
    @Override
    public void replaceIfGreater(StudyGroup item, int key) throws NoSuchIdException {
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
    @Override
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
    @Override
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
    @Override
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
    @Override
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
    @Override
    public List<Semester> printFieldAscendingSemesterEnum() {
        return data.values()
                .stream()
                .map(StudyGroup::getSemesterEnum)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public boolean canSaveData() {
        return true;
    }

    @Override
    public StudyGroup checkGroup(Integer id) {
        return data.get(id);
    }


}
