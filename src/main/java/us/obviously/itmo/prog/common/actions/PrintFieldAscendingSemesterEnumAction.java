package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.model.Semester;
import us.obviously.itmo.prog.common.serializers.SemesterListSerializer;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;
import us.obviously.itmo.prog.server.exceptions.UsedKeyException;

import java.util.List;

public class PrintFieldAscendingSemesterEnumAction extends Action<VoidModel, List<Semester>> {
    public PrintFieldAscendingSemesterEnumAction(DataCollection dataCollection) {
        super(dataCollection, "ascending-semester", new VoidSerializer(), new SemesterListSerializer());
    }

    @Override
    public List<Semester> execute(VoidModel arguments) throws UsedKeyException {
        return this.getDataCollection().printFieldAscendingSemesterEnum();
    }
}
