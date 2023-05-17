package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.LocalDataCollection;
import us.obviously.itmo.prog.server.exceptions.CantWriteDataException;
import us.obviously.itmo.prog.server.exceptions.FailedToDumpsEx;
import us.obviously.itmo.prog.server.exceptions.FileNotWritableException;

public class SaveDataAction extends Action<VoidModel, VoidModel> {
    public SaveDataAction() {
        super("save");
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, VoidModel arguments) {
        try {
            dataCollection.saveData();
        } catch (FailedToDumpsEx e) {
            return new Response(e.getMessage(), ResponseStatus.SERVER_ERROR);
        } catch (CantWriteDataException e) {
            return new Response("Запись недоступна", ResponseStatus.FORBIDDEN);
        } catch (FileNotWritableException e) {
            return new Response("Файл нельзя писать", ResponseStatus.FORBIDDEN);
        }
        return new Response(this.getResponse().serialize(new VoidModel()), ResponseStatus.OK);
    }
}
