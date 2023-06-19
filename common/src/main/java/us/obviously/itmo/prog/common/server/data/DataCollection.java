package us.obviously.itmo.prog.common.server.data;

import us.obviously.itmo.prog.common.UserInfo;
import us.obviously.itmo.prog.common.UserInfoExplicit;
import us.obviously.itmo.prog.common.action_models.UserModel;
import us.obviously.itmo.prog.common.server.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.model.Person;
import us.obviously.itmo.prog.common.model.Semester;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.common.server.exceptions.NoSuchIdException;
import us.obviously.itmo.prog.common.server.exceptions.UsedKeyException;

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

    default UserInfo loginUser(UserModel userModel) throws BadRequestException {
        return new UserInfoExplicit(0, "", "");
    }

    default UserInfo registerUser(UserModel userModel) throws BadRequestException {
        return new UserInfoExplicit(0, "", "");
    }

    StudyGroup checkGroup(Integer id) throws BadRequestException;


    default void ping() throws BadRequestException {}
}
