package us.obviously.itmo.prog.actions;

import us.obviously.itmo.prog.action_models.VoidModel;
import us.obviously.itmo.prog.server.data.LocalDataCollection;

import java.sql.SQLException;

public class ClearDataAction extends Action<VoidModel, VoidModel> {
    public ClearDataAction() {
        super("clear");
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, VoidModel arguments) {
        try {
            getDatabaseManager().removeAllUserObjects(getUserInfo().getId());
        } catch (SQLException e) {
            return new Response("Ошибка во время удаления ваших объектов", ResponseStatus.SERVER_ERROR);
        }
        dataCollection.removeUserItems(getUserInfo().getId());
        return new Response(this.getResponse().serialize(new VoidModel()), ResponseStatus.OK);
    }
}
