package lotto.common.config;

public enum UserRule {
    WINING_NUMBERS_SEPARATOR(",");

    private final String value;

    UserRule(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
