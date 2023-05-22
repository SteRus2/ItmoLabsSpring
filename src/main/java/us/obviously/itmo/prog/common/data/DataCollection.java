package us.obviously.itmo.prog.common.data;

import us.obviously.itmo.prog.common.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.exceptions.ServerErrorException;
import us.obviously.itmo.prog.common.model.Person;
import us.obviously.itmo.prog.common.model.Semester;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.server.exceptions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DataCollection {
    DataInfo getInfo() throws BadRequestException, ServerErrorException;

    HashMap<Integer, StudyGroup> getData() throws BadRequestException, ServerErrorException;

    void insertItem(StudyGroup item, int key) throws UsedKeyException, BadRequestException, ServerErrorException;

    void updateItem(StudyGroup item, int key) throws NoSuchIdException, BadRequestException, ServerErrorException;

    void removeItem(int key) throws NoSuchIdException, BadRequestException, ServerErrorException;

    void clearData() throws BadRequestException, ServerErrorException;

    void saveData() throws FailedToDumpsEx, CantWriteDataException, FileNotWritableException, BadRequestException, ServerErrorException;

    void replaceIfGreater(StudyGroup item, int key) throws NoSuchIdException, BadRequestException, ServerErrorException;

    void removeGreaterKey(int key) throws BadRequestException, ServerErrorException;

    void removeLowerKey(int key) throws BadRequestException, ServerErrorException;

    Map<String, List<StudyGroup>> groupCountingByName() throws BadRequestException, ServerErrorException;

    List<StudyGroup> filterGreaterThanGroupAdmin(Person groupAdmin) throws BadRequestException, ServerErrorException;

    List<Semester> printFieldAscendingSemesterEnum() throws BadRequestException, ServerErrorException;

    boolean canSaveData() throws BadRequestException, ServerErrorException;


}
