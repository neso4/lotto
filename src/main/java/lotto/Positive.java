package lotto;

public class Positive implements Comparable<Positive> {
    private int number;
    private static final int ZERO = 0;

    public Positive(String value) {
        this(Integer.parseInt(value));
    }

    public Positive(int number) {
        validatePositive(number);
        this.number = number;
    }

    private void validatePositive(int number) {
        if (isMinusOrZero(number)) {
            throw new IllegalArgumentException();
        }
    }

    public Positive add(Positive other) {
        return new Positive(this.number + other.number);
    }

    public Positive divide(Positive other) {
        validateBigger(other);
        return new Positive(doDivide(other));
    }

    private int doDivide(Positive other) {
        return this.number / other.number;
    }

    private void validateBigger(Positive other) {
        if (isNotPositive(other)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isNotPositive(Positive other) {
        return this.number < other.number;
    }

    public int getNumber() {
        return number;
    }

    private boolean isMinusOrZero(int number) {
        return number <= ZERO;
    }

    @Override
    public int compareTo(Positive o) {
        return this.number - o.number;
    }
}
