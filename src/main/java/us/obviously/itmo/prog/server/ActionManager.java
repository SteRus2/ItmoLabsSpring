package us.obviously.itmo.prog.server;

import us.obviously.itmo.prog.common.actions.*;
import us.obviously.itmo.prog.server.exceptions.ActionDoesNotExistException;

import java.util.HashMap;
import java.util.Map;

public class ActionManager {
    private final Map<String, Action<?, ?>> actionMap;

    public ActionManager() {
        actionMap = new HashMap<>();
        var canSaveDataAction = new CanSaveDataAction();
        var clearDataAction = new ClearDataAction();
        var filterGreaterAction = new FilterGreaterThanGroupAdminAction();
        var getDataAction = new GetDataAction();
        var getInfoAction = new GetInfoAction();
        var groupCountingAction = new GroupCountingByNameAction();
        var insertItemAction = new InsertItemAction();
        var printFieldAction = new PrintFieldAscendingSemesterEnumAction();
        var removeGreaterAction = new RemoveGreaterKeyAction();
        var removeItemAction = new RemoveItemAction();
        var removeLowerAction = new RemoveLowerKeyAction();
        var replaceGreaterAction = new ReplaceIfGreaterAction();
        var saveDataAction = new SaveDataAction();
        var updateItemAction = new UpdateItemAction();
        var checkGroupAction = new CheckGroupAction();
        var pingAction = new PingAction();
        var loginAction = new LoginAction();
        var registerAction = new RegisterAction();

        actionMap.put(canSaveDataAction.getName(), canSaveDataAction);
        actionMap.put(clearDataAction.getName(), clearDataAction);
        actionMap.put(filterGreaterAction.getName(), filterGreaterAction);
        actionMap.put(getDataAction.getName(), getDataAction);
        actionMap.put(getInfoAction.getName(), getInfoAction);
        actionMap.put(groupCountingAction.getName(), groupCountingAction);
        actionMap.put(insertItemAction.getName(), insertItemAction);
        actionMap.put(printFieldAction.getName(), printFieldAction);
        actionMap.put(removeGreaterAction.getName(), removeGreaterAction);
        actionMap.put(removeItemAction.getName(), removeItemAction);
        actionMap.put(removeLowerAction.getName(), removeLowerAction);
        actionMap.put(replaceGreaterAction.getName(), replaceGreaterAction);
        actionMap.put(saveDataAction.getName(), saveDataAction);
        actionMap.put(updateItemAction.getName(), updateItemAction);
        actionMap.put(checkGroupAction.getName(), checkGroupAction);
        actionMap.put(pingAction.getName(), pingAction);
        actionMap.put(loginAction.getName(), loginAction);
        actionMap.put(registerAction.getName(), registerAction);
    }

    public Action<?, ?> getAction(String name) throws ActionDoesNotExistException {
        var action = actionMap.get(name.toLowerCase().trim());
        if (action == null) {
            throw new ActionDoesNotExistException("Действие не сертифицировано");
        }
        return action;

    }
}
