package us.obviously.itmo.prog.fields;

public class FloatField extends ComparableField<Float> {
    public FloatField(Boolean nil, Float defaultValue) {
        super(nil, defaultValue);
    }

    public FloatField(Boolean nil) {
        super(nil);
    }

    public FloatField() {
        super();
    }
}
