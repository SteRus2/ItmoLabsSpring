package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.server.data.LocalDataCollection;
import us.obviously.itmo.prog.common.model.Semester;

import java.util.List;

public class PrintFieldAscendingSemesterEnumAction extends Action<VoidModel, List<Semester>> {
    public PrintFieldAscendingSemesterEnumAction() {
        super("ascending-semester");
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, VoidModel arguments) {
        var result = dataCollection.printFieldAscendingSemesterEnum();
        var body = this.getResponse().serialize(result);
        return new Response(body, ResponseStatus.OK);
    }
}
