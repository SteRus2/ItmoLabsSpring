package us.obviously.itmo.prog.common.data;

import us.obviously.itmo.prog.common.model.Person;
import us.obviously.itmo.prog.common.model.Semester;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.server.exceptions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RemoteDataCollection {
    DataInfo getInfo();

    HashMap<Integer, StudyGroup> getData();

    void insertItem(StudyGroup item, int key) throws UsedKeyException;

    void updateItem(StudyGroup item, int key) throws NoSuchIdException;

    void removeItem(int key) throws NoSuchIdException;

    void clearData();

    void saveData() throws FailedToDumpsEx, CantWriteDataException, FileNotWritableException;

    void replaceIfGreater(StudyGroup item, int key) throws NoSuchIdException;

    void removeGreaterKey(int key);

    void removeLowerKey(int key);

    Map<String, List<StudyGroup>> groupCountingByName();

    List<StudyGroup> filterGreaterThanGroupAdmin(Person groupAdmin);

    List<Semester> printFieldAscendingSemesterEnum();

    boolean canSaveData();
}
