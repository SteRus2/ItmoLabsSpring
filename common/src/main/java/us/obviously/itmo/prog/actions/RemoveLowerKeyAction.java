package us.obviously.itmo.prog.actions;

import us.obviously.itmo.prog.action_models.KeyModel;
import us.obviously.itmo.prog.action_models.VoidModel;
import us.obviously.itmo.prog.server.data.LocalDataCollection;

import java.sql.SQLException;

public class RemoveLowerKeyAction extends Action<KeyModel, VoidModel> {
    public RemoveLowerKeyAction() {
        super("remove-lower");
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, KeyModel arguments) {
        try {
            getDatabaseManager().removeLowerUserObjects(arguments.getKey(), getUserInfo().getId());
        } catch (SQLException e) {
            return new Response("Ошибка во время удаления ваших объектов", ResponseStatus.SERVER_ERROR);
        }
        dataCollection.removeLowerKey(arguments.getKey(), getUserInfo().getId());
        return new Response(this.getResponse().serialize(new VoidModel()), ResponseStatus.OK);
    }
}
