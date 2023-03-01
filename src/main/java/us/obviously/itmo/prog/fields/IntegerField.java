package us.obviously.itmo.prog.fields;

public class IntegerField extends ComparableField<Integer> {
    public IntegerField(Boolean nil, Integer defaultValue) {
        super(nil, defaultValue);
    }

    public IntegerField(Boolean nil) {
        super(nil);
    }

    public IntegerField() {
        super();
    }
}
