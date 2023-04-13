package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.client.Client;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.model.Person;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.common.serializers.PersonSerializer;
import us.obviously.itmo.prog.common.serializers.StudyGroupListSerializer;

import java.util.List;

public class FilterGreaterThanGroupAdminAction extends Action<Person, List<StudyGroup>> {
    public FilterGreaterThanGroupAdminAction() {
        super("info", new PersonSerializer(), new StudyGroupListSerializer());
    }

    @Override
    public List<StudyGroup> execute(DataCollection dataCollection, Person arguments) {
        return dataCollection.filterGreaterThanGroupAdmin(arguments);
    }
}
