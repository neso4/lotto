package lotto.model.constants;

public enum RegexPattern {
    REGEX_MULTIPLE_1000("([1-9]+)(0{3,})"),
    REGEX_NUMERIC("^[1-9]\\d*"),
    REGEX_NUMERIC_WITH_COMMA("(\\d+,){5}\\d+");

    private final String regex;

    RegexPattern(String regex) {
        this.regex = regex;
    }

    public String get(){
        return regex;
    }
}
