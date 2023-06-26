package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.KeyGroupModel;
import us.obviously.itmo.prog.common.server.data.LocalDataCollection;
import us.obviously.itmo.prog.common.server.exceptions.UsedKeyException;

import java.sql.SQLException;

public class InsertItemAction extends Action<KeyGroupModel, Integer> {
    public InsertItemAction() {
        super("insert");
    }


    @Override
    public Response execute(LocalDataCollection dataCollection, KeyGroupModel arguments) {
        Integer newId;
        try {
            newId = getDatabaseManager().insertItem(arguments, getUserInfo());
            arguments.getStudyGroup().setId(newId);
            arguments.getStudyGroup().setOwnerUsername(getUserInfo().getLogin());
            dataCollection.insertItem(arguments.getStudyGroup(), newId, getUserInfo().getId());
            // notifyUsers();
        } catch (UsedKeyException e) {
            return new Response("Ключ уже используется", ResponseStatus.BAD_REQUEST);
        } catch (SQLException e) {
            return new Response("Непредвиденная ошибка во время добавления элемента", ResponseStatus.BAD_REQUEST);
        }
        return new Response(this.getResponse().serialize(newId), ResponseStatus.OK);
    }
}
