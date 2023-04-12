package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;
import us.obviously.itmo.prog.server.exceptions.CantWriteDataException;
import us.obviously.itmo.prog.server.exceptions.FailedToDumpsEx;
import us.obviously.itmo.prog.server.exceptions.FileNotWritableException;
import us.obviously.itmo.prog.server.exceptions.NoSuchIdException;

public class SaveDataAction extends Action<VoidModel, VoidModel> {
    public SaveDataAction(DataCollection dataCollection) {
        super(dataCollection, "save", new VoidSerializer(), new VoidSerializer());
    }

    @Override
    public VoidModel execute(VoidModel arguments) throws FileNotWritableException, FailedToDumpsEx, CantWriteDataException {
        this.getDataCollection().saveData();
        return new VoidModel();
    }
}
