package us.obviously.itmo.prog.client;

import us.obviously.itmo.prog.common.action_models.KeyGroupModel;
import us.obviously.itmo.prog.common.action_models.KeyModel;
import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.actions.*;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.data.DataInfo;
import us.obviously.itmo.prog.common.model.Person;
import us.obviously.itmo.prog.common.model.Semester;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.server.exceptions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteDataCollection implements DataCollection {
    private Client client;
    private String answer;
    private Map<String, Action<Object, Object>> actions;

    public RemoteDataCollection(Client client) {
        this.client = client;
        this.actions = new HashMap<>();
    }

    public void addAction(Action<Object, Object> action) {
        this.actions.put(action.getName(), action);
    }

    @Override
    public DataInfo getInfo() {
        return new GetInfoAction().send(this.client, new VoidModel());
    }

    @Override
    public HashMap<Integer, StudyGroup> getData() {
        return new GetDataAction().send(this.client, new VoidModel());
    }

    @Override
    public void insertItem(StudyGroup item, int key) throws UsedKeyException {
        new InsertItemAction().send(this.client, new KeyGroupModel(item, key));
    }

    @Override
    public void updateItem(StudyGroup item, int key) throws NoSuchIdException {
        new UpdateItemAction().send(this.client, new KeyGroupModel(item, key));
    }

    @Override
    public void removeItem(int key) throws NoSuchIdException {
        new RemoveItemAction().send(this.client, new KeyModel(key));
    }

    @Override
    public void clearData() {
        new ClearDataAction().send(this.client, new VoidModel());
    }

    @Override
    public void saveData() throws FailedToDumpsEx, CantWriteDataException, FileNotWritableException {
        new SaveDataAction().send(this.client, new VoidModel());
    }

    @Override
    public void replaceIfGreater(StudyGroup item, int key) throws NoSuchIdException {
        new ReplaceIfGreaterAction().send(this.client, new KeyGroupModel(item, key));
    }

    @Override
    public void removeGreaterKey(int key) {
        new RemoveGreaterKeyAction().send(this.client, new KeyModel(key));
    }

    @Override
    public void removeLowerKey(int key) {
        new RemoveLowerKeyAction().send(this.client, new KeyModel(key));
    }

    @Override
    public Map<String, List<StudyGroup>> groupCountingByName() {
        return new GroupCountingByNameAction().send(this.client, new VoidModel());
    }

    @Override
    public List<StudyGroup> filterGreaterThanGroupAdmin(Person groupAdmin) {
        return new FilterGreaterThanGroupAdminAction().send(this.client, groupAdmin);
    }

    @Override
    public List<Semester> printFieldAscendingSemesterEnum() {
        return new PrintFieldAscendingSemesterEnumAction().send(this.client, new VoidModel());
    }

    @Override
    public boolean canSaveData() {
        return new CanSaveDataAction().send(this.client, new VoidModel());
    }


}
