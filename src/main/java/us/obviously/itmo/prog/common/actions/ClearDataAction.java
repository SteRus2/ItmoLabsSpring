package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.LocalDataCollection;

import java.sql.SQLException;

public class ClearDataAction extends Action<VoidModel, VoidModel> {
    public ClearDataAction() {
        super("clear");
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, VoidModel arguments) {
        try {
            getDatabaseManager().removeAllUserObjects(getUserInfo().getLogin());
        } catch (SQLException e) {
            return new Response("Ошибка во время удаления ваших объектов", ResponseStatus.SERVER_ERROR);
        }
        dataCollection.removeUserItems(getUserInfo().getLogin());
        return new Response(this.getResponse().serialize(new VoidModel()), ResponseStatus.OK);
    }
}
