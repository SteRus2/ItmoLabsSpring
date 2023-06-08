package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.KeyModel;
import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.LocalDataCollection;
import us.obviously.itmo.prog.server.exceptions.NoSuchIdException;
import us.obviously.itmo.prog.server.exceptions.NotAccessException;

import java.sql.SQLException;

public class RemoveItemAction extends Action<KeyModel, VoidModel> {
    public RemoveItemAction() {
        super("remove");
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, KeyModel arguments) {
        try {
            getDatabaseManager().removeUserObject(arguments.getKey(), getUserInfo().getId());
            dataCollection.removeUserItem(arguments.getKey(), getUserInfo().getId());
        } catch (NoSuchIdException e) {
            return new Response("Элемента с таким id не существует", ResponseStatus.NOT_FOUND);
        } catch (NotAccessException e) {
            return new Response(e.getMessage(), ResponseStatus.NOT_FOUND);
        } catch (SQLException e) {
            return new Response("Ошибка баз данных", ResponseStatus.NOT_FOUND);
        }
        return new Response(this.getResponse().serialize(new VoidModel()), ResponseStatus.OK);
    }
}
