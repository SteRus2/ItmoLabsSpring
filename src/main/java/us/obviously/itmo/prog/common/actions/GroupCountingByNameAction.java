package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.common.serializers.GroupCountingSerializer;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;

import java.util.List;
import java.util.Map;

public class GroupCountingByNameAction extends Action<VoidModel, Map<String, List<StudyGroup>>> {
    public GroupCountingByNameAction() {
        super("counting-name", new VoidSerializer(), new GroupCountingSerializer());
    }

    @Override
    public Map<String, List<StudyGroup>> execute(DataCollection dataCollection, VoidModel arguments) {
        return dataCollection.groupCountingByName();
    }
}
