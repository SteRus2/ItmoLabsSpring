package us.obviously.itmo.prog.common.action_models;

import us.obviously.itmo.prog.common.model.StudyGroup;

public class KeyGroupModel extends Model {
    private int key;
    private StudyGroup studyGroup;

    public KeyGroupModel(int key, StudyGroup studyGroup) {
        this.key = key;
        this.studyGroup = studyGroup;
    }

    public int getKey() {
        return key;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }
}
