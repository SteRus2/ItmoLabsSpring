package us.obviously.itmo.prog.fields;

/**
 * Общийс
 */
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
