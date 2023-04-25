package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.KeyModel;
import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.serializers.KeySerializer;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;
import us.obviously.itmo.prog.server.exceptions.NoSuchIdException;

public class RemoveItemAction extends Action<KeyModel, VoidModel> {
    public RemoveItemAction() {
        super("remove", new KeySerializer(), new VoidSerializer());
    }

    @Override
    public Response execute(DataCollection dataCollection, KeyModel arguments) throws NoSuchIdException {
        dataCollection.removeItem(arguments.getKey());
//        return new VoidModel();
        return new Response("It's fine", ResponseStatus.OK);
    }
}
