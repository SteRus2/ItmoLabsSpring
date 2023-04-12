package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.KeyModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.serializers.BooleanSerializer;
import us.obviously.itmo.prog.common.serializers.KeySerializer;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;

public class CanSaveDataAction extends Action<KeyModel, Boolean> {
    public CanSaveDataAction(DataCollection dataCollection) {
        super(dataCollection, "can-save", new KeySerializer(), new BooleanSerializer());
    }

    @Override
    public Boolean execute(KeyModel arguments) {
        return this.getDataCollection().canSaveData();
    }
}
