package us.obviously.itmo.prog.client;


import us.obviously.itmo.prog.client.exceptions.FailedToReadRemoteException;
import us.obviously.itmo.prog.common.action_models.KeyGroupModel;
import us.obviously.itmo.prog.common.action_models.KeyModel;
import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.actions.*;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.data.DataInfo;
import us.obviously.itmo.prog.common.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.model.Person;
import us.obviously.itmo.prog.common.model.Semester;
import us.obviously.itmo.prog.common.model.StudyGroup;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteDataCollection implements DataCollection {
    private final Client client;
    private final Map<String, Action<Object, Object>> actions;

    public RemoteDataCollection(Client client)  {
        this.client = client;
        this.actions = new HashMap<>();
    }

    public void addAction(Action<Object, Object> action) throws BadRequestException {
        this.actions.put(action.getName(), action);
    }

    @Override
    public DataInfo getInfo() throws BadRequestException {
        var rm = new RequestManager<VoidModel, DataInfo>();
        rm.send(client, new VoidModel(), "filter_greater_than_group_admin");
        try{
            return rm.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());

        }
    }

    @Override
    public HashMap<Integer, StudyGroup> getData() throws BadRequestException {
        var rm = new RequestManager<VoidModel, HashMap<Integer, StudyGroup>>();
        rm.send(client, new VoidModel(), "data");
        try {
            return rm.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());
        }
        /*var action = new GetDataAction();
        new GetDataAction().send(this.client, new VoidModel());
        try {
            return action.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());
        }*/
    }

    @Override
    public void insertItem(StudyGroup item, int key) throws BadRequestException {
        var rm = new RequestManager<KeyGroupModel, VoidModel>();
        rm.send(client, new KeyGroupModel(item, key), "insert");
        try {
            rm.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());
        }

        /*var action = new InsertItemAction();
        action.send(this.client, new KeyGroupModel(item, key));
        try {
            action.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());
        }*/
    }

    @Override
    public void updateItem(StudyGroup item, int key) throws BadRequestException {
        var rm = new RequestManager<KeyGroupModel, VoidModel>();
        rm.send(client, new KeyGroupModel(item, key), "update");
        try {
            rm.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());
        }

        /*var action = new UpdateItemAction();
        action.send(this.client, new KeyGroupModel(item, key));
        try {
            action.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());
        }*/
    }

    @Override
    public void removeItem(int key) throws BadRequestException {

        var rm = new RequestManager<KeyModel, VoidModel>();
        rm.send(client, new KeyModel(key), "remove");
        try {
            rm.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());
        }

        /*var action = new RemoveItemAction();
        action.send(this.client, new KeyModel(key));
        try {
            action.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());
        }*/
    }

    @Override
    public void clearData() throws BadRequestException {
        var rm = new RequestManager<VoidModel, VoidModel>();
        rm.send(client, new VoidModel(), "clear");
        try {
            rm.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());
        }

        /*var action = new ClearDataAction();
        action.send(this.client, new VoidModel());
        try {
            action.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());
        }*/
    }

    @Override
    public void saveData() throws BadRequestException {

        var rm = new RequestManager<VoidModel, VoidModel>();
        rm.send(client, new VoidModel(), "save");
        try {
            rm.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());
        }

        /*var action = new SaveDataAction();
        action.send(this.client, new VoidModel());
        try {
            action.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());

        }*/
    }

    @Override
    public void replaceIfGreater(StudyGroup item, int key) throws BadRequestException {
        var rm = new RequestManager<KeyGroupModel, VoidModel>();
        rm.send(client, new KeyGroupModel(item, key), "replace-greater");
        try {
            rm.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());
        }

        /*var action = new ReplaceIfGreaterAction();
        action.send(this.client, new KeyGroupModel(item, key));
        try {
            action.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());

        }*/
    }

    @Override
    public void removeGreaterKey(int key) throws BadRequestException {
        var rm = new RequestManager<KeyModel, VoidModel>();
        rm.send(client, new KeyModel(key), "remove-greater");
        try {
            rm.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());
        }


        /*var action = new RemoveGreaterKeyAction();
        action.send(this.client, new KeyModel(key));
        try {
            action.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());

        }*/
    }

    @Override
    public void removeLowerKey(int key) throws BadRequestException {
        var rm = new RequestManager<KeyModel, VoidModel>();
        rm.send(client, new KeyModel(key), "remove-lower");
        try {
            rm.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());
        }


        /*var action = new RemoveLowerKeyAction();
        action.send(this.client, new KeyModel(key));
        try {
            action.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());

        }*/
    }

    @Override
    public Map<String, List<StudyGroup>> groupCountingByName() throws BadRequestException {
        var rm = new RequestManager<VoidModel, Map<String, List<StudyGroup>>>();
        rm.send(client, new VoidModel(), "counting-name");
        try {
            return rm.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());
        }

        /*var action = new GroupCountingByNameAction();
        action.send(this.client, new VoidModel());
        try {
            return action.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());
        }*/
    }

    @Override
    public List<StudyGroup> filterGreaterThanGroupAdmin(Person groupAdmin) throws BadRequestException {

        var rm = new RequestManager<Person, List<StudyGroup>>();
        rm.send(client, groupAdmin, "info");
        try {
            return rm.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());
        }

        /*var action = new FilterGreaterThanGroupAdminAction();
        action.send(this.client, groupAdmin);
        try {
            return action.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());

        }*/
    }

    @Override
    public List<Semester> printFieldAscendingSemesterEnum() throws BadRequestException {
        var action = new PrintFieldAscendingSemesterEnumAction();
        action.send(this.client, new VoidModel());
        try {
            return action.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());

        }
    }

    @Override
    public boolean canSaveData() throws BadRequestException {

        var rm = new RequestManager<VoidModel, Boolean>();
        rm.send(client, new VoidModel(), "can-save");
        try {
            return rm.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());
        }

        /*var action = new CanSaveDataAction();
        action.send(this.client, new VoidModel());
        try {
            return action.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());

        }*/
    }


}


