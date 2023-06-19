package us.obviously.itmo.prog.actions;

import us.obviously.itmo.prog.action_models.VoidModel;
import us.obviously.itmo.prog.server.data.LocalDataCollection;
import us.obviously.itmo.prog.model.Semester;

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
