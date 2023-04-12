package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.KeyGroupModel;
import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.serializers.KeyGroupSerializer;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;
import us.obviously.itmo.prog.server.exceptions.UsedKeyException;

public class InsertItemAction extends Action<KeyGroupModel, VoidModel> {
    public InsertItemAction(DataCollection dataCollection) {
        super(dataCollection, "insert", new KeyGroupSerializer(), new VoidSerializer());
    }

    @Override
    public VoidModel execute(KeyGroupModel arguments) throws UsedKeyException {
        this.getDataCollection().insertItem(arguments.getStudyGroup(), arguments.getKey());
        return new VoidModel();
    }
}
