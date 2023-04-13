package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.model.Semester;
import us.obviously.itmo.prog.common.serializers.SemesterListSerializer;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;

import java.util.List;

public class PrintFieldAscendingSemesterEnumAction extends Action<VoidModel, List<Semester>> {
    public PrintFieldAscendingSemesterEnumAction() {
        super("ascending-semester", new VoidSerializer(), new SemesterListSerializer());
    }

    @Override
    public List<Semester> execute(DataCollection dataCollection, VoidModel arguments) {
        return dataCollection.printFieldAscendingSemesterEnum();
    }
}
