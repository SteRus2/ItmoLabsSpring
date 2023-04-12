package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.common.serializers.DataSerializer;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;
import us.obviously.itmo.prog.server.exceptions.UsedKeyException;

import java.util.HashMap;

public class GetDataAction extends Action<VoidModel, HashMap<Integer, StudyGroup>> {
    public GetDataAction(DataCollection dataCollection) {
        super(dataCollection, "data", new VoidSerializer(), new DataSerializer());
    }

    @Override
    public HashMap<Integer, StudyGroup> execute(VoidModel arguments) throws UsedKeyException {
        return this.getDataCollection().getData();
    }
}
