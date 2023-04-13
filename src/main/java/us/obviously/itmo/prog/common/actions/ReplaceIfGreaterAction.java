package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.client.Client;
import us.obviously.itmo.prog.common.action_models.KeyGroupModel;
import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.serializers.KeyGroupSerializer;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;
import us.obviously.itmo.prog.server.exceptions.NoSuchIdException;

public class ReplaceIfGreaterAction extends Action<KeyGroupModel, VoidModel> {
    public ReplaceIfGreaterAction() {
        super("replace-greater", new KeyGroupSerializer(), new VoidSerializer());
    }

    @Override
    public VoidModel execute(DataCollection dataCollection, KeyGroupModel arguments) throws NoSuchIdException {
        dataCollection.replaceIfGreater(arguments.getStudyGroup(), arguments.getKey());
        return new VoidModel();
    }
}
