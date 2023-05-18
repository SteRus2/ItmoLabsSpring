package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.LocalDataCollection;

public class ClearDataAction extends Action<VoidModel, VoidModel> {
    public ClearDataAction() {
        super("clear");
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, VoidModel arguments) {
        dataCollection.clearData();
        return new Response(this.getResponse().serialize(new VoidModel()), ResponseStatus.OK);
    }
}
