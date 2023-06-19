package us.obviously.itmo.prog.actions;

import us.obviously.itmo.prog.action_models.VoidModel;
import us.obviously.itmo.prog.server.data.LocalDataCollection;

public class SaveDataAction extends Action<VoidModel, VoidModel> {
    public SaveDataAction() {
        super("save");
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, VoidModel arguments) {
        dataCollection.saveData();
        return new Response(this.getResponse().serialize(new VoidModel()), ResponseStatus.OK);
    }
}
