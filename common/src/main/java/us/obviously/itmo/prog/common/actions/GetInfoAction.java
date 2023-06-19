package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.server.data.DataInfo;
import us.obviously.itmo.prog.common.server.data.LocalDataCollection;

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
