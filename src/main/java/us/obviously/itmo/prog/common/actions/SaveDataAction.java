package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;
import us.obviously.itmo.prog.server.exceptions.CantWriteDataException;
import us.obviously.itmo.prog.server.exceptions.FailedToDumpsEx;
import us.obviously.itmo.prog.server.exceptions.FileNotWritableException;

public class SaveDataAction extends Action<VoidModel, VoidModel> {
    public SaveDataAction() {
        super("save", new VoidSerializer(), new VoidSerializer());
    }

    @Override
    public VoidModel execute(DataCollection dataCollection, VoidModel arguments) throws FileNotWritableException, FailedToDumpsEx, CantWriteDataException {
        dataCollection.saveData();
        return new VoidModel();
    }
}
