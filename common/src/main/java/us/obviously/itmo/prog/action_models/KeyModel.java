package us.obviously.itmo.prog.action_models;

import java.io.Serializable;

public class KeyModel extends Model implements Serializable {
    private final int key;

    public KeyModel(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
