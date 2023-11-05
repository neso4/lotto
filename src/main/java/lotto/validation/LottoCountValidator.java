package lotto.validation;

public class LottoCountValidator implements InputValidator {
    private final static int MIN_VALUE = 0;

    public void validate(String input) {
        if (!isInteger(input)) {
            Error error = Error.INTEGER_ERROR;
            throw new IllegalArgumentException(error.message());
        }

        int number = Integer.parseInt(input);
        if (!isBiggerThan(number, MIN_VALUE)) {
            Error error = Error.POSITIVE_ERROR;
            throw new IllegalArgumentException(error.message());
        }
        if (!isDivisibleBy1000(number)) {
            Error error = Error.DIVISIBLE_ERROR;
            throw new IllegalArgumentException(error.message());
        }
    }

    @Override
    public boolean isDivisibleBy1000(int input) {
        return input % 1000 == 0;
    }
}
