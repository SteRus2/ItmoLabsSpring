package us.obviously.itmo.prog.common.action_models;

import us.obviously.itmo.prog.common.model.StudyGroup;

import java.io.Serializable;

public class KeyGroupModel extends Model implements Serializable {
    private final int key;
    private final StudyGroup studyGroup;

    public KeyGroupModel(StudyGroup studyGroup, int key) {
        this.studyGroup = studyGroup;
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }
}
