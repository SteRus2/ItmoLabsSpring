package us.obviously.itmo.prog.actions;

import us.obviously.itmo.prog.action_models.VoidModel;
import us.obviously.itmo.prog.server.data.LocalDataCollection;
import us.obviously.itmo.prog.model.StudyGroup;

import java.util.List;
import java.util.Map;

public class GroupCountingByNameAction extends Action<VoidModel, Map<String, List<StudyGroup>>> {
    public GroupCountingByNameAction() {
        super("counting-name");
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, VoidModel arguments) {
        var result = dataCollection.groupCountingByName();

        var body = this.getResponse().serialize(result);
        return new Response(body, ResponseStatus.OK);
    }
}
