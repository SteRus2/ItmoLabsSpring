package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.KeyGroupModel;
import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.LocalDataCollection;
import us.obviously.itmo.prog.server.exceptions.NoSuchIdException;

public class UpdateItemAction extends Action<KeyGroupModel, VoidModel> {
    public UpdateItemAction() {
        super("update");
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, KeyGroupModel arguments) {
        try {
            dataCollection.updateItem(arguments.getStudyGroup(), arguments.getKey());
        } catch (NoSuchIdException e) {
            return new Response("Элемента с таким id не существует", ResponseStatus.NOT_FOUND);
        }
        return new Response(this.getResponse().serialize(new VoidModel()), ResponseStatus.OK);
    }
}
