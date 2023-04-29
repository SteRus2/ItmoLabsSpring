package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.KeyGroupModel;
import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.data.LocalDataCollection;
import us.obviously.itmo.prog.common.serializers.KeyGroupSerializer;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;
import us.obviously.itmo.prog.server.exceptions.UsedKeyException;

public class InsertItemAction extends Action<KeyGroupModel, VoidModel> {
    public InsertItemAction() {
        super("insert", new KeyGroupSerializer(), new VoidSerializer());
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, KeyGroupModel arguments) {
        try {
            dataCollection.insertItem(arguments.getStudyGroup(), arguments.getKey());
        } catch (UsedKeyException e) {
            return new Response("Ключ уже используется", ResponseStatus.BAD_REQUEST);
        }
        return new Response(this.getResponse().serialize(new VoidModel()), ResponseStatus.OK);
    }
}
