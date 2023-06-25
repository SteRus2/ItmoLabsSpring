package us.obviously.itmo.prog.client;


import us.obviously.itmo.prog.common.server.exceptions.FailedToReadRemoteException;
import us.obviously.itmo.prog.common.server.exceptions.JwtTokenExpiredException;
import us.obviously.itmo.prog.common.UserInfo;
import us.obviously.itmo.prog.common.UserInfoPublic;
import us.obviously.itmo.prog.common.action_models.KeyGroupModel;
import us.obviously.itmo.prog.common.action_models.KeyModel;
import us.obviously.itmo.prog.common.action_models.UserModel;
import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.server.data.DataCollection;
import us.obviously.itmo.prog.common.server.data.DataInfo;
import us.obviously.itmo.prog.common.server.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.model.Person;
import us.obviously.itmo.prog.common.model.Semester;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.common.server.exceptions.InvalidTokenException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteDataCollection implements DataCollection {
    private final Client client;

    public RemoteDataCollection(Client client) {
        this.client = client;
    }

    @Override
    public DataInfo getInfo() throws BadRequestException {
        var rm = new RequestManager<VoidModel, DataInfo>();
        rm.send(client, new VoidModel(), "filter_greater_than_group_admin");
        try {
            return rm.receive(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public HashMap<Integer, StudyGroup> getData() throws BadRequestException {
        var rm = new RequestManager<VoidModel, HashMap<Integer, StudyGroup>>();
        rm.send(client, new VoidModel(), "data");
        try {
            return rm.receive(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public Integer insertItem(StudyGroup item, int key) throws BadRequestException {
        var rm = new RequestManager<KeyGroupModel, Integer>();
        rm.send(client, new KeyGroupModel(item, key), "insert");
        try {
            return rm.receive(client);
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
            rm.receive(client);
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
            rm.receive(client);
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
            rm.receive(client);
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
            rm.receive(client);
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
            rm.receive(client);
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
            rm.receive(client);
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
            rm.receive(client);
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
            return rm.receive(client);
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
            return rm.receive(client);
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
        var rm = new RequestManager<VoidModel, List<Semester>>();
        rm.send(client, new VoidModel(), "ascending-semester");
        try {
            return rm.receive(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());
        }


        /*var action = new PrintFieldAscendingSemesterEnumAction();
        action.send(this.client, new VoidModel());
        try {
            return action.recieve(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());

        }*/
    }

    @Override
    public boolean canSaveData() throws BadRequestException {

        var rm = new RequestManager<VoidModel, Boolean>();
        rm.send(client, new VoidModel(), "can-save");
        try {
            return rm.receive(client);
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

    @Override
    public UserInfo loginUser(UserModel user) throws BadRequestException {
        var rm = new RequestManager<UserModel, String>();
        rm.send(client, user, "login");
        try {
            var token = rm.receive(client);
            var userInfo = parseToken(token);
            client.setId(userInfo.getId());
            client.setLogin(userInfo.getLogin());
            client.setPassword(user.getPassword());
            client.setAuthToken(token);
            return userInfo;
        } catch (FailedToReadRemoteException | InvalidTokenException | JwtTokenExpiredException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public UserInfo registerUser(UserModel user) throws BadRequestException {
        var rm = new RequestManager<UserModel, String>();
        rm.send(client, user, "register");
        try {
            var token = rm.receive(client);
            var userInfo = parseToken(token);
            client.setId(userInfo.getId());
            client.setLogin(userInfo.getLogin());
            client.setPassword(user.getPassword());
            client.setAuthToken(token);
            return userInfo;
        } catch (FailedToReadRemoteException | InvalidTokenException | JwtTokenExpiredException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    private UserInfo parseToken(String result) throws InvalidTokenException, JwtTokenExpiredException {
        var validator = new JwtClientValidator();
        var token = validator.validate(result);
        int id = Integer.parseInt(token.getClaim("id").asString());
        String username = token.getClaim("username").asString();
        return new UserInfoPublic(id, username);
    }

    @Override
    public StudyGroup checkGroup(Integer id) throws BadRequestException {
        var rm = new RequestManager<Integer, StudyGroup>();
        rm.send(client, id, "check");
        try {
            return rm.receive(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public void ping() throws BadRequestException {
        var rm = new RequestManager<VoidModel, VoidModel>();
        rm.send(client, new VoidModel(), "ping");
        try {
            rm.receive(client);
        } catch (FailedToReadRemoteException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}


