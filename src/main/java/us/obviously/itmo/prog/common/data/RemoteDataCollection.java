package us.obviously.itmo.prog.common.data;

import us.obviously.itmo.prog.common.model.Person;
import us.obviously.itmo.prog.common.model.Semester;
import us.obviously.itmo.prog.common.model.StudyGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RemoteDataCollection {
    DataInfo getInfo();

    HashMap<Integer, StudyGroup> getData();

    void insertItem(StudyGroup item, int key);

    void updateItem(StudyGroup item, int key);

    void removeItem(int key);

    void clearData();

    void saveData();

    void replaceIfGreater(StudyGroup item, int key);

    void removeGreaterKey(int key);

    void removeLowerKey(int key);

    Map<String, List<StudyGroup>> groupCountingByName();

    List<StudyGroup> filterGreaterThanGroupAdmin(Person groupAdmin);

    List<Semester> printFieldAscendingSemesterEnum();

    boolean canSaveData();
}
