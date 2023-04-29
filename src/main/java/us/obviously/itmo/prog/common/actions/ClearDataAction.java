package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.LocalDataCollection;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;

public class ClearDataAction extends Action<VoidModel, VoidModel> {
    public ClearDataAction() {
        super("clear", new VoidSerializer(), new VoidSerializer());
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, VoidModel arguments) {
        dataCollection.clearData();
        return new Response(this.getResponse().serialize(new VoidModel()), ResponseStatus.OK);
    }
}
