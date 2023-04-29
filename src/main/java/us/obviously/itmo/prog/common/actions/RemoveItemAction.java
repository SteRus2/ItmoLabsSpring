package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.KeyModel;
import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.LocalDataCollection;
import us.obviously.itmo.prog.common.serializers.KeySerializer;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;
import us.obviously.itmo.prog.server.exceptions.NoSuchIdException;

import java.nio.charset.StandardCharsets;

public class RemoveItemAction extends Action<KeyModel, VoidModel> {
    public RemoveItemAction() {
        super("remove", new KeySerializer(), new VoidSerializer());
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, KeyModel arguments) {
        try {
            dataCollection.removeItem(arguments.getKey());
        } catch (NoSuchIdException e) {
            return new Response("Элемента с таким id не существует", ResponseStatus.NOT_FOUND);
        }
        return new Response(this.getResponse().serialize(new VoidModel()) , ResponseStatus.OK);
    }
}
