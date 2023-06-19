package us.obviously.itmo.prog.actions;

import us.obviously.itmo.prog.action_models.KeyGroupModel;
import us.obviously.itmo.prog.action_models.VoidModel;
import us.obviously.itmo.prog.server.data.LocalDataCollection;
import us.obviously.itmo.prog.server.exceptions.NoSuchIdException;

public class ReplaceIfGreaterAction extends Action<KeyGroupModel, VoidModel> {
    public ReplaceIfGreaterAction() {
        super("replace-greater");
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, KeyGroupModel arguments) {
        try {
            if (dataCollection.getData().get(arguments.getKey()).compareTo(arguments.getStudyGroup()) <= 0) {
                return new Response(this.getResponse().serialize(new VoidModel()), ResponseStatus.OK);
            }
            var updated = getDatabaseManager().updateItem(arguments.getStudyGroup(), arguments.getKey(), getUserInfo().getId());
            if (!updated) {
                return new Response("Не удалось обновить объект, причина не известна", ResponseStatus.BAD_REQUEST);
            }
            dataCollection.replaceIfGreater(arguments.getStudyGroup(), arguments.getKey());
        } catch (NoSuchIdException e) {
            return new Response("Элемента с таким id не существует", ResponseStatus.NOT_FOUND);
        }
        return new Response(this.getResponse().serialize(new VoidModel()), ResponseStatus.OK);
    }
}
