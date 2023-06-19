package us.obviously.itmo.prog.actions;

import us.obviously.itmo.prog.action_models.VoidModel;
import us.obviously.itmo.prog.server.data.LocalDataCollection;

public class CanSaveDataAction extends Action<VoidModel, Boolean> {
    public CanSaveDataAction() {
        super("can-save");
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, VoidModel arguments) {

        byte[] body = this.getResponse().serialize(dataCollection.canSaveData());
        return new Response(body, ResponseStatus.OK);

    }
}
