package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.client.Client;
import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.data.DataInfo;
import us.obviously.itmo.prog.common.serializers.DataInfoSerializer;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;
import us.obviously.itmo.prog.server.exceptions.FailedToDumpsEx;
import us.obviously.itmo.prog.server.exceptions.UsedKeyException;

public class GetInfoAction extends Action<VoidModel, DataInfo> {
    public GetInfoAction() {
        super("info", new VoidSerializer(), new DataInfoSerializer());
    }

    @Override
    public Response execute(DataCollection dataCollection, VoidModel arguments) {
        var result = dataCollection.getInfo();
        try {
            String body = this.getResponse().serialize(result);
            return new Response(body, ResponseStatus.OK);
        } catch (FailedToDumpsEx e) {
            return new Response(e.getMessage(), ResponseStatus.SERVER_ERROR);
        }
    }
}
