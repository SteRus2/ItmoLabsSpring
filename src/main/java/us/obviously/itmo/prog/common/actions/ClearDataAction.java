package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.serializers.KeySerializer;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;
import us.obviously.itmo.prog.server.exceptions.NoSuchIdException;

public class ClearDataAction extends Action<VoidModel, VoidModel> {
    public ClearDataAction(DataCollection dataCollection) {
        super(dataCollection, "clear", new VoidSerializer(), new VoidSerializer());
    }

    @Override
    public VoidModel execute(VoidModel arguments) {
        this.getDataCollection().clearData();
        return new VoidModel();
    }
}
