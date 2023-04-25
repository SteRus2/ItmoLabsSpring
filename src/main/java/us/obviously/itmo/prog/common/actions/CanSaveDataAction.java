package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.serializers.BooleanSerializer;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;
import us.obviously.itmo.prog.server.exceptions.FailedToDumpsEx;

public class CanSaveDataAction extends Action<VoidModel, Boolean> {
    public CanSaveDataAction() {
        super("can-save", new VoidSerializer(), new BooleanSerializer());
    }

    @Override
    public Response execute(DataCollection dataCollection, VoidModel arguments) {
        try {
            String body = this.getResponse().serialize(dataCollection.canSaveData());
            return new Response(body, ResponseStatus.OK);
        } catch (FailedToDumpsEx e) {
            return new Response(e.getMessage(), ResponseStatus.SERVER_ERROR);
        }
    }
}
