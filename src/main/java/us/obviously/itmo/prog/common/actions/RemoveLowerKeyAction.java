package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.KeyModel;
import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.LocalDataCollection;

public class RemoveLowerKeyAction extends Action<KeyModel, VoidModel> {
    public RemoveLowerKeyAction() {
        super("remove-lower");
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, KeyModel arguments) {
        dataCollection.removeLowerKey(arguments.getKey());
        return new Response(this.getResponse().serialize(new VoidModel()), ResponseStatus.OK);
    }
}
