package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.common.serializers.GroupCountingSerializer;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;
import us.obviously.itmo.prog.server.exceptions.UsedKeyException;

import java.util.List;
import java.util.Map;

public class GroupCountingByNameAction extends Action<VoidModel, Map<String, List<StudyGroup>>> {
    public GroupCountingByNameAction(DataCollection dataCollection) {
        super(dataCollection, "counting-name", new VoidSerializer(), new GroupCountingSerializer());
    }

    @Override
    public Map<String, List<StudyGroup>> execute(VoidModel arguments) throws UsedKeyException {
        return this.getDataCollection().groupCountingByName();
    }
}
