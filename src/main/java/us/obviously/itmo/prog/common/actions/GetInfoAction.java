package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.DataInfo;
import us.obviously.itmo.prog.common.data.LocalDataCollection;
import us.obviously.itmo.prog.common.serializers.DataInfoSerializer;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;
import us.obviously.itmo.prog.server.exceptions.FailedToDumpsEx;

public class GetInfoAction extends Action<VoidModel, DataInfo> {
    public GetInfoAction() {
        super("info", new VoidSerializer(), new DataInfoSerializer());
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, VoidModel arguments) {
        var result = dataCollection.getInfo();
        try {
            var body = this.getResponse().serialize(result);
            return new Response(body, ResponseStatus.OK);
        } catch (FailedToDumpsEx e) {
            return new Response(e.getMessage(), ResponseStatus.SERVER_ERROR);
        }
    }
}
