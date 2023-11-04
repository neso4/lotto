package lotto.constant;

public enum NumberRange {
    LOWEST_NUMBER(1),
    HIGHEST_NUMBER(45);

    private final int number;
    NumberRange(int number) {
        this.number = number;
    }
    public int getRangeNumber() {
        return number;
    }
}
