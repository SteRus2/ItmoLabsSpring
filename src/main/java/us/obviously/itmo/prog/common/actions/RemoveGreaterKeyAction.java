package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.KeyModel;
import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.LocalDataCollection;

public class RemoveGreaterKeyAction extends Action<KeyModel, VoidModel> {
    public RemoveGreaterKeyAction() {
        super("remove-greater");
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, KeyModel arguments) {
        dataCollection.removeGreaterKey(arguments.getKey());
        return new Response(this.getResponse().serialize(new VoidModel()), ResponseStatus.OK);
    }
}
