package us.obviously.itmo.prog;

import com.fasterxml.jackson.core.JsonProcessingException;
import us.obviously.itmo.prog.exceptions.*;
import us.obviously.itmo.prog.model.Person;
import us.obviously.itmo.prog.model.Semester;
import us.obviously.itmo.prog.model.StudyGroup;
import us.obviously.itmo.prog.reader.DataReader;

import java.io.IOException;
import java.util.*;

public class DataCollection {
    private DataReader dataReader;
    private HashMap<Integer, StudyGroup> data;
    private String type;
    private Date initDate;
    private int count;

    public DataCollection(DataReader dataReader) throws IncorrectValueException, IncorrectValuesTypeException, CantParseDataException {
        this.dataReader = dataReader;
        this.data = dataReader.getData();
        type = StudyGroup.class.getName();
        initDate = new Date();
    }

    public DataInfo getInfo() {
        count = data.size();
        return new DataInfo(type, initDate, count);
    }

    public HashMap<Integer, StudyGroup> getData() {
        return data;
    }

    public void insertItem(StudyGroup item, int key) throws UsedKeyException {
        if (data.containsKey(key)) {
            throw new UsedKeyException("К сожалению, ключ уже используется");
        } else {
            data.put(key, item);
        }
    }

    public void updateItem(StudyGroup item, int key) throws NoSuchIdException {
        if (!data.containsKey(key)) {
            throw new NoSuchIdException("Объекта с таким id нет в коллекции");
        }
        item.setCreationDate(data.get(key).getCreationDate());
        data.put(key, item);
    }

    public void removeItem(int key) throws NoSuchIdException {
        if (!data.containsKey(key)) {
            throw new NoSuchIdException("Объекта с таким id нет в коллекции");
        }
        data.remove(key);
    }

    public void clearData() {
        data.clear();
    }

    public void saveData() throws FailedToDumpsEx, CantWriteDataException {
        dataReader.saveData(data);
    }

    public void replaceIfGreater(int key, StudyGroup item) throws NoSuchIdException {
        if (!data.containsKey(key)) {
            throw new NoSuchIdException("Объекта с таким id нет в коллекции");
        }
        if (data.get(key).compareTo(item) > 0) {
            data.put(key, item);
        }
    }

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

    public List<StudyGroup> filterGreaterThanGroupAdmin(Person groupAdmin) {
        List<StudyGroup> local = new ArrayList<>();
        for (Map.Entry<Integer, StudyGroup> pair : data.entrySet()) {
            if (pair.getValue().getGroupAdmin().compareTo(groupAdmin) < 0) {
                local.add(pair.getValue());
            }
        }
        return local;
    }

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
