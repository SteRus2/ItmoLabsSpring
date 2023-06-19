package us.obviously.itmo.prog.actions;

import us.obviously.itmo.prog.action_models.VoidModel;
import us.obviously.itmo.prog.server.data.LocalDataCollection;
import us.obviously.itmo.prog.model.StudyGroup;

import java.util.HashMap;

public class GetDataAction extends Action<VoidModel, HashMap<Integer, StudyGroup>> {
    public GetDataAction() {
        super("data");
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, VoidModel arguments) {
        var result = dataCollection.getData();

        var body = this.getResponse().serialize(result);
        return new Response(body, ResponseStatus.OK);
    }
}
