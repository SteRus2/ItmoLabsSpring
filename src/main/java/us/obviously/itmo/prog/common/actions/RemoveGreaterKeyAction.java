package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.KeyModel;
import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.serializers.KeySerializer;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;

public class RemoveGreaterKeyAction extends Action<KeyModel, VoidModel> {
    public RemoveGreaterKeyAction() {
        super("remove-greater", new KeySerializer(), new VoidSerializer());
    }

    @Override
    public VoidModel execute(DataCollection dataCollection, KeyModel arguments) {
        dataCollection.removeGreaterKey(arguments.getKey());
        return new VoidModel();
    }
}
