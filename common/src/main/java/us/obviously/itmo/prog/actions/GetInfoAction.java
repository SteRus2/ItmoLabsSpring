package us.obviously.itmo.prog.actions;

import us.obviously.itmo.prog.action_models.VoidModel;
import us.obviously.itmo.prog.server.data.DataInfo;
import us.obviously.itmo.prog.server.data.LocalDataCollection;

public class GetInfoAction extends Action<VoidModel, DataInfo> {
    public GetInfoAction() {
        super("filter_greater_than_group_admin");
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, VoidModel arguments) {
        var result = dataCollection.getInfo();
        var body = this.getResponse().serialize(result);
        return new Response(body, ResponseStatus.OK);
    }
}
