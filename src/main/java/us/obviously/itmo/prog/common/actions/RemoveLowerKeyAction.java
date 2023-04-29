package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.client.Client;
import us.obviously.itmo.prog.common.action_models.KeyModel;
import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.data.LocalDataCollection;
import us.obviously.itmo.prog.common.serializers.KeySerializer;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;

public class RemoveLowerKeyAction extends Action<KeyModel, VoidModel> {
    public RemoveLowerKeyAction() {
        super("remove-lower", new KeySerializer(), new VoidSerializer());
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, KeyModel arguments) {
        dataCollection.removeLowerKey(arguments.getKey());
        return new Response(this.getResponse().serialize(new VoidModel()), ResponseStatus.OK);
    }
}
