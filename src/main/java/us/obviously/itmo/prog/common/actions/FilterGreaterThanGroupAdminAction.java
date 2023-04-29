package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.client.Client;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.data.LocalDataCollection;
import us.obviously.itmo.prog.common.model.Person;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.common.serializers.PersonSerializer;
import us.obviously.itmo.prog.common.serializers.StudyGroupListSerializer;
import us.obviously.itmo.prog.server.exceptions.FailedToDumpsEx;

import java.util.List;

public class FilterGreaterThanGroupAdminAction extends Action<Person, List<StudyGroup>> {
    public FilterGreaterThanGroupAdminAction() {
        super("info", new PersonSerializer(), new StudyGroupListSerializer());
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, Person arguments) {
        var result = dataCollection.filterGreaterThanGroupAdmin(arguments);
        var body = this.getResponse().serialize(result);
        return new Response(body, ResponseStatus.OK);
    }
}
