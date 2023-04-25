package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.common.serializers.DataSerializer;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;
import us.obviously.itmo.prog.server.exceptions.FailedToDumpsEx;

import java.util.HashMap;

public class GetDataAction extends Action<VoidModel, HashMap<Integer, StudyGroup>> {
    public GetDataAction() {
        super("data", new VoidSerializer(), new DataSerializer());
    }

    @Override
    public Response execute(DataCollection dataCollection, VoidModel arguments) {
        var result = dataCollection.getData();
        try {
            String body = this.getResponse().serialize(result);
            return new Response(body, ResponseStatus.OK);
        } catch (FailedToDumpsEx e) {
            return new Response(e.getMessage(), ResponseStatus.SERVER_ERROR);
        }
    }
}
