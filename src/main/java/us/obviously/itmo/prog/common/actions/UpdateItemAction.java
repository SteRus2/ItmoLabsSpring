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
            boolean updated = getDatabaseManager().updateItem(arguments.getStudyGroup(), arguments.getKey(), getUserInfo().getLogin());
            if (!updated) {
                return new Response("Не удалось обновить объект, причина не известна", ResponseStatus.BAD_REQUEST);
            }
            dataCollection.updateItem(arguments.getStudyGroup(), arguments.getKey(), getUserInfo().getLogin());
        } catch (NoSuchIdException e) {
            return new Response("Элемента с таким id не существует", ResponseStatus.NOT_FOUND);
        }
        return new Response(this.getResponse().serialize(new VoidModel()), ResponseStatus.OK);
    }
}
