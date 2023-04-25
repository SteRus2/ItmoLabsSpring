package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.model.Semester;
import us.obviously.itmo.prog.common.serializers.SemesterListSerializer;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;
import us.obviously.itmo.prog.server.exceptions.FailedToDumpsEx;

import java.util.List;

public class PrintFieldAscendingSemesterEnumAction extends Action<VoidModel, List<Semester>> {
    public PrintFieldAscendingSemesterEnumAction() {
        super("ascending-semester", new VoidSerializer(), new SemesterListSerializer());
    }

    @Override
    public Response execute(DataCollection dataCollection, VoidModel arguments) {
        var result = dataCollection.printFieldAscendingSemesterEnum();
        try {
            String body = this.getResponse().serialize(result);
            return new Response(body, ResponseStatus.OK);
        } catch (FailedToDumpsEx e) {
            return new Response(e.getMessage(), ResponseStatus.SERVER_ERROR);
        }
    }
}
