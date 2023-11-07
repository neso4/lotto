package lotto.enumerate;

public enum ConfigString {
    WINNING_STRING_FIRST("당첨 통계\n---\n"),
    REPLACE_TARGET(" "),
    REPLACE_REPLACEMENT(""),
    SPLIT_REGEX(","),
    STRING_FORMAT("%%.%df"),
    STRING_INDEX(".");

    private final String string;

    ConfigString(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
