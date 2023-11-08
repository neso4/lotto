package lotto.constant;

public enum MaxRetry {
    COUNT(3);

    private final int value;

    MaxRetry(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
