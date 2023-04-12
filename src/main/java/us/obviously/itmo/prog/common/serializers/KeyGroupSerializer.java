package us.obviously.itmo.prog.common.serializers;

import us.obviously.itmo.prog.common.action_models.KeyGroupModel;
import us.obviously.itmo.prog.common.model.StudyGroup;

public class KeyGroupSerializer implements Serializer<KeyGroupModel> {

    @Override
    public String serialize(KeyGroupModel object) {
        // TODO: Сериализуем получается
        return String.valueOf(object.getKey());
    }

    @Override
    public KeyGroupModel parse(String body) {
        int key = Integer.parseInt(body);
        // TODO: Распарсивание
        return new KeyGroupModel(key, new StudyGroup());
    }
}