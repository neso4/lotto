package lotto.enums;

public enum Delimiter {
    COMMA(",", "^\\d+(,\\d+)*$", "쉼표");

    private final String value;
    private final String regex;
    private final String korName;

    Delimiter(String value, String regex, String korName) {
        this.value = value;
        this.regex = regex;
        this.korName = korName;
    }

    public String getValue() {
        return value;
    }

    public String getRegex() {
        return regex;
    }

    public String getKorName() {
        return korName;
    }
}
