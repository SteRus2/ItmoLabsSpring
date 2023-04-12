package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.KeyModel;
import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.serializers.KeySerializer;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;

public class RemoveLowerKeyAction extends Action<KeyModel, VoidModel> {
    public RemoveLowerKeyAction(DataCollection dataCollection) {
        super(dataCollection, "remove-lower", new KeySerializer(), new VoidSerializer());
    }

    @Override
    public VoidModel execute(KeyModel arguments) {
        this.getDataCollection().removeLowerKey(arguments.getKey());
        return new VoidModel();
    }
}
