package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.KeyGroupModel;
import us.obviously.itmo.prog.common.action_models.KeyModel;
import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.serializers.KeyGroupSerializer;
import us.obviously.itmo.prog.common.serializers.KeySerializer;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;
import us.obviously.itmo.prog.server.exceptions.NoSuchIdException;
import us.obviously.itmo.prog.server.exceptions.UsedKeyException;

public class RemoveItemAction extends Action<KeyModel, VoidModel> {
    public RemoveItemAction(DataCollection dataCollection) {
        super(dataCollection, "remove", new KeySerializer(), new VoidSerializer());
    }

    @Override
    public VoidModel execute(KeyModel arguments) throws NoSuchIdException {
        this.getDataCollection().removeItem(arguments.getKey());
        return new VoidModel();
    }
}
