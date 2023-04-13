package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.serializers.BooleanSerializer;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;

public class CanSaveDataAction extends Action<VoidModel, Boolean> {
    public CanSaveDataAction() {
        super("can-save", new VoidSerializer(), new BooleanSerializer());
    }

    @Override
    public Boolean execute(DataCollection dataCollection, VoidModel arguments) {
        return dataCollection.canSaveData();
    }
}
