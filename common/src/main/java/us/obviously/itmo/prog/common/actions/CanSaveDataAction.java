package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.server.data.LocalDataCollection;

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
