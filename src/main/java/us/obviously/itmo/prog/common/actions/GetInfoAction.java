package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.data.DataInfo;
import us.obviously.itmo.prog.common.serializers.DataInfoSerializer;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;
import us.obviously.itmo.prog.server.exceptions.UsedKeyException;

public class GetInfoAction extends Action<VoidModel, DataInfo> {
    public GetInfoAction(DataCollection dataCollection) {
        super(dataCollection, "info", new VoidSerializer(), new DataInfoSerializer());
    }

    @Override
    public DataInfo execute(VoidModel arguments) throws UsedKeyException {
        return this.getDataCollection().getInfo();
    }
}
