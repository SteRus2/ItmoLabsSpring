package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.client.Client;
import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;

public class ClearDataAction extends Action<VoidModel, VoidModel> {
    public ClearDataAction() {
        super("clear", new VoidSerializer(), new VoidSerializer());
    }

    @Override
    public VoidModel execute(DataCollection dataCollection, VoidModel arguments) {
        dataCollection.clearData();
        return new VoidModel();
    }
}
