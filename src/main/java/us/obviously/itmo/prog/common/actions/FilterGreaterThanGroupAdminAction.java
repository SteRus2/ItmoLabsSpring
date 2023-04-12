package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.model.Person;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.common.serializers.GroupCountingSerializer;
import us.obviously.itmo.prog.common.serializers.PersonSerializer;
import us.obviously.itmo.prog.common.serializers.StudyGroupListSerializer;
import us.obviously.itmo.prog.common.serializers.VoidSerializer;
import us.obviously.itmo.prog.server.exceptions.UsedKeyException;

import java.util.List;
import java.util.Map;

public class FilterGreaterThanGroupAdminAction extends Action<Person, List<StudyGroup>> {
    public FilterGreaterThanGroupAdminAction(DataCollection dataCollection) {
        super(dataCollection, "info", new PersonSerializer(), new StudyGroupListSerializer());
    }

    @Override
    public List<StudyGroup> execute(Person arguments) throws UsedKeyException {
        return this.getDataCollection().filterGreaterThanGroupAdmin(arguments);
    }
}
