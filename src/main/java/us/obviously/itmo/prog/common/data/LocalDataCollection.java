package us.obviously.itmo.prog.common.data;

import us.obviously.itmo.prog.common.model.Person;
import us.obviously.itmo.prog.common.model.Semester;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.server.exceptions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface LocalDataCollection extends DataCollection {
    DataInfo getInfo();

    HashMap<Integer, StudyGroup> getData();

    Integer insertItem(StudyGroup item, int key) throws UsedKeyException;

    Integer insertItem(StudyGroup item, int key, int ownerId) throws UsedKeyException;


    void updateItem(StudyGroup item, int key) throws NoSuchIdException;
    void updateItem(StudyGroup item, int key, int ownerId) throws NoSuchIdException;


    void removeItem(int key) throws NoSuchIdException;

    void clearData();

    void saveData();

    void replaceIfGreater(StudyGroup item, int key) throws NoSuchIdException;

    void removeGreaterKey(int key);

    void removeGreaterKey(int key, int ownerId);


    void removeLowerKey(int key);

    void removeLowerKey(int key, int ownerId);


    Map<String, List<StudyGroup>> groupCountingByName();

    List<StudyGroup> filterGreaterThanGroupAdmin(Person groupAdmin);

    List<Semester> printFieldAscendingSemesterEnum();

    boolean canSaveData();

    StudyGroup checkGroup(Integer id);

    void removeUserItems(int ownerId);


    void removeUserItem(int key, int ownerId) throws NotAccessException, NoSuchIdException;
}
