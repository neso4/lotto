package lotto;

public record Money(int amount) {
    private static final int MINIMAL_UNIT = 1_000;

    public Money {
        validate(amount);
    }

    private void validate(int amount) {
        validateUnit(amount);
        validateNotZero(amount);
    }

    private void validateUnit(int amount) {
        if (amount % MINIMAL_UNIT != 0) {
            throw new IllegalArgumentException();
        }
    }

    private void validateNotZero(int amount) {
        if (amount == 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return amount == money.amount;
    }

    public int perUnit() {
        return amount / MINIMAL_UNIT;
    }
}
