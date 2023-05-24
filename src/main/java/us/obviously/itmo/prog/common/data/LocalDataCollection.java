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

    Integer insertItem(StudyGroup item, int key, String login) throws UsedKeyException;


    void updateItem(StudyGroup item, int key) throws NoSuchIdException;

    void removeItem(int key) throws NoSuchIdException;

    void clearData();

    void saveData() throws FailedToDumpsEx, CantWriteDataException, FileNotWritableException;

    void replaceIfGreater(StudyGroup item, int key) throws NoSuchIdException;

    void removeGreaterKey(int key);

    void removeGreaterKey(int key, String login);


    void removeLowerKey(int key);

    void removeLowerKey(int key, String login);


    Map<String, List<StudyGroup>> groupCountingByName();

    List<StudyGroup> filterGreaterThanGroupAdmin(Person groupAdmin);

    List<Semester> printFieldAscendingSemesterEnum();

    boolean canSaveData();

    StudyGroup checkGroup(Integer id);

    void removeUserItems(String login);


    void removeUserItem(int key, String login) throws NotAccessException, NoSuchIdException;
}
