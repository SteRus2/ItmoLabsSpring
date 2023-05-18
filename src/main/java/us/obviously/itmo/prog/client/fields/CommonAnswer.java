package us.obviously.itmo.prog.client.fields;

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
