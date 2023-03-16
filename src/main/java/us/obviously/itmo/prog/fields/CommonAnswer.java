package us.obviously.itmo.prog.fields;

public enum CommonAnswer {
    YES("y"),
    NO("n");

    private final String word;

    CommonAnswer(String word) {
        this.word = word;
    }

    public String getWord() {
        return this.word;
    }
}
