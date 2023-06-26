package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.server.data.LocalDataCollection;
import us.obviously.itmo.prog.common.model.Person;
import us.obviously.itmo.prog.common.model.StudyGroup;

import java.util.List;

public class FilterGreaterThanGroupAdminAction extends Action<Person, List<StudyGroup>> {
    public FilterGreaterThanGroupAdminAction() {
        super("filter_greater_than_group_admin");
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, Person arguments) {
        var result = dataCollection.filterGreaterThanGroupAdmin(arguments);
        var body = this.getResponse().serialize(result);
        return new Response(body, ResponseStatus.OK);
    }
}
