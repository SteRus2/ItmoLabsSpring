package us.obviously.itmo.prog.common.data;

import us.obviously.itmo.prog.common.UserInfo;
import us.obviously.itmo.prog.common.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.model.Person;
import us.obviously.itmo.prog.common.model.Semester;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.server.exceptions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DataCollection {
    DataInfo getInfo() throws BadRequestException;

    HashMap<Integer, StudyGroup> getData() throws BadRequestException;

    Integer insertItem(StudyGroup item, int key) throws UsedKeyException, BadRequestException;

    void updateItem(StudyGroup item, int key) throws NoSuchIdException, BadRequestException;

    void removeItem(int key) throws NoSuchIdException, BadRequestException;

    void clearData() throws BadRequestException;

    void saveData() throws BadRequestException;

    void replaceIfGreater(StudyGroup item, int key) throws NoSuchIdException, BadRequestException;

    void removeGreaterKey(int key) throws BadRequestException;

    void removeLowerKey(int key) throws BadRequestException;

    Map<String, List<StudyGroup>> groupCountingByName() throws BadRequestException;

    List<StudyGroup> filterGreaterThanGroupAdmin(Person groupAdmin) throws BadRequestException;

    List<Semester> printFieldAscendingSemesterEnum() throws BadRequestException;

    boolean canSaveData() throws BadRequestException;

    default String loginUser(UserInfo userInfo) throws BadRequestException {
        return "";
    }

    default String registerUser(UserInfo userInfo) throws BadRequestException {
        return "";
    }

    StudyGroup checkGroup(Integer id) throws BadRequestException;


}
