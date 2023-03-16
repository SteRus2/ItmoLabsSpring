package us.obviously.itmo.prog;

import us.obviously.itmo.prog.exceptions.*;
import us.obviously.itmo.prog.model.Person;
import us.obviously.itmo.prog.model.Semester;
import us.obviously.itmo.prog.model.StudyGroup;
import us.obviously.itmo.prog.reader.DataReader;

import java.util.*;

/**
 * Класс, предоставляющий основной функционал по работе с коллекцией данных
 */
public class DataCollection {
    private DataReader dataReader;
    private HashMap<Integer, StudyGroup> data;
    private String type;
    private Date initDate;
    private int count;

    /**
     * Конструктор, с помощью которого происходит инициализация коллекции, коллекцию получаем из dataReader
     * @param dataReader Источник данных
     * @throws IncorrectValueException Выбросит исключение, если данные не пройдут валидацию
     * @throws IncorrectValuesTypeException Выбросит исключение, если данные будут в неправильном формате
     * @throws CantParseDataException Выбросит исключение, если не сможет перевести дынные в нужный формат
     * @throws CantFindFileException Выбросит исключение, если не сможет найти путь до данных
     */
    public DataCollection(DataReader dataReader) throws IncorrectValueException, IncorrectValuesTypeException, CantParseDataException, CantFindFileException {
        this.dataReader = dataReader;
        this.data = dataReader.getData();
        type = StudyGroup.class.getName();
        initDate = new Date();
    }

    /**
     * Метод, для получения информации о коллекции
     * @return Информацию о коллекцию в формате объекта
     * @see DataInfo
     */
    public DataInfo getInfo() {
        count = data.size();
        return new DataInfo(type, initDate, count);
    }

    /**
     * Возвращает Коллекцию
     * @return Коллекция
     */
    public HashMap<Integer, StudyGroup> getData() {
        return data;
    }

    /**
     * Метод, позволяющий добавить в коллекцию новую запись с новым ключом
     * @param item Новый элемент коллекции
     * @param key Ключ элемента
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
     * @param item Новый элемент
     * @param key Ключ элемента, который нужно обновить
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
     * @param key Ключ объекта
     * @throws NoSuchIdException Выбросит исключение, если элемента под таким ключом нет
     * <br>(ну, к слову, семантика этого метода такова - по итогу в коллекции не должно быть элемента с ключом key,
     * <br>в целом, если его там и так не было, то зачем выбрасывать исключение? Элемента в коллекции уже нет, разработчики решили, что надо)
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
     * @throws FailedToDumpsEx Выбросит исключение, если не сможет перевести коллекцию в нужный формат
     * @throws CantWriteDataException Выбросит исключение, если не сможет записать данные
     */
    public void saveData() throws FailedToDumpsEx, CantWriteDataException {
        dataReader.saveData(data);
    }

    /**
     * Метод, позволяющий заменить объект по заданному ключу новым объектом, если новый объект больше старого
     * @param key Ключ объекта
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
     * @param key Ключ
     */
    public void removeGreaterKey(int key) {
        List<Integer> ids = new ArrayList<>();
        for (Map.Entry<Integer, StudyGroup> pair : data.entrySet()) {
            if (pair.getKey() > key) {
                ids.add(pair.getKey());
            }
        }
        for (Integer k : ids){
            data.remove(k);
        }
    }

    /**
     * Метод, позволяющий удалить из коллекции все элементы, ключ которых меньше заданного
     * @param key Ключ
     */
    public void removeLowerKey(int key) {
        List<Integer> ids = new ArrayList<>();
        for (Map.Entry<Integer, StudyGroup> pair : data.entrySet()) {
            if (pair.getKey() < key) {
                ids.add(pair.getKey());
            }
        }
        for (Integer k : ids){
            data.remove(k);
        }
    }

    /**
     * Метод, который позволяет сгруппировать элементы коллекции по значению поля name
     * @return Возвращает HashMap с ключом в виде имени и значением - List со всеми элементами с данным именем
     */
    public Map<String, List<StudyGroup>> groupCountingByName() {
        HashMap<String, List<StudyGroup>> localData = new HashMap<>();
        for (Map.Entry<Integer, StudyGroup> pair : data.entrySet()) {
            if (!localData.containsKey(pair.getValue().getName())) {
                localData.put(pair.getValue().getName(), new ArrayList<>(List.of(pair.getValue())));
            } else {
                List<StudyGroup> llsg = localData.get(pair.getValue().getName());
                llsg.add(pair.getValue());
                localData.put(pair.getValue().getName(), llsg);
            }
        }
        return localData;
    }

    /**
     * Метод, который позволяет получить все элементы, поле groupAdmin которых больше чем данный
     * @param groupAdmin groupAdmin, по которому происходит проверка
     * @return List, состоящий из всех элементов поле groupAdmin которых больше чем данный
     */
    public List<StudyGroup> filterGreaterThanGroupAdmin(Person groupAdmin) {
        List<StudyGroup> local = new ArrayList<>();
        for (Map.Entry<Integer, StudyGroup> pair : data.entrySet()) {
            if (pair.getValue().getGroupAdmin().compareTo(groupAdmin) < 0) {
                local.add(pair.getValue());
            }
        }
        return local;
    }

    /**
     * Метод, позволяющий получить значения поля semesterEnum все элементов в порядке возрастания
     * @return List из элементов типа Semester
     */
    public List<Semester> printFieldAscendingSemesterEnum() {
        List<StudyGroup> local = new ArrayList<>(data.values());
        List<Semester> result = new ArrayList<>();
        local.sort(new Comparator<StudyGroup>() {
            @Override
            public int compare(StudyGroup o1, StudyGroup o2) {
                return o1.compareTo(o2);
            }
        });
        for (StudyGroup sg : local) {
            result.add(sg.getSemesterEnum());
        }
        return result;
    }
}
