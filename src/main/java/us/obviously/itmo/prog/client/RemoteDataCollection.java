package us.obviously.itmo.prog.client;

import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.exceptions.FailedToReadRemoteException;
import us.obviously.itmo.prog.client.exceptions.FailedToSentRequestsException;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.data.DataInfo;
import us.obviously.itmo.prog.common.model.Person;
import us.obviously.itmo.prog.common.model.Semester;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.server.exceptions.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteDataCollection implements DataCollection {
    private Client client;
    private String answer;
    public RemoteDataCollection(Client client){
        this.client = client;
    }
    @Override
    public DataInfo getInfo() {
        try {
            client.request("getInfo");
            answer = client.waitResponse();
        } catch (FailedToSentRequestsException e) {
            Messages.printStatement("~reМы не смогли отправить запрос. " + e.getMessage() + "~=");
        } catch (FailedToReadRemoteException e) {
            Messages.printStatement("~reМы не смогли получить ответ от сервера. " + e.getMessage() + "~=");
        }
        return new DataInfo(answer, new Date(), 9);
    }

    @Override
    public HashMap<Integer, StudyGroup> getData() {
        return null;
    }

    @Override
    public void insertItem(StudyGroup item, int key) throws UsedKeyException {

    }

    @Override
    public void updateItem(StudyGroup item, int key) throws NoSuchIdException {

    }

    @Override
    public void removeItem(int key) throws NoSuchIdException {

    }

    @Override
    public void clearData() {

    }

    @Override
    public void saveData() throws FailedToDumpsEx, CantWriteDataException, FileNotWritableException {

    }

    @Override
    public void replaceIfGreater(StudyGroup item, int key) throws NoSuchIdException {

    }


    @Override
    public void removeGreaterKey(int key) {

    }

    @Override
    public void removeLowerKey(int key) {

    }

    @Override
    public Map<String, List<StudyGroup>> groupCountingByName() {
        return null;
    }

    @Override
    public List<StudyGroup> filterGreaterThanGroupAdmin(Person groupAdmin) {
        return null;
    }

    @Override
    public List<Semester> printFieldAscendingSemesterEnum() {
        return null;
    }

    @Override
    public boolean canSaveData() {
        return false;
    }


}
