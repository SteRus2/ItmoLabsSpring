package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.LocalDataCollection;
import us.obviously.itmo.prog.common.serializers.BooleanSerializer;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;
import us.obviously.itmo.prog.server.exceptions.FailedToDumpsEx;

public class CanSaveDataAction extends Action<VoidModel, Boolean> {
    public CanSaveDataAction() {
        super("can-save", new VoidSerializer(), new BooleanSerializer());
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, VoidModel arguments) {

        byte[] body = this.getResponse().serialize(dataCollection.canSaveData());
        return new Response(body, ResponseStatus.OK);

    }
}
