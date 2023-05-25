package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.LocalDataCollection;

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
