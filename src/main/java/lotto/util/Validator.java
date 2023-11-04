package lotto.util;

import static lotto.Constant.BUY_UNIT;

import java.util.List;
import java.util.regex.Pattern;

public enum Validator {
    INSTANCE;
    private static final int NUMBER_LOW_BOUND = 1;
    private static final int NUMBER_HIGH_BOUND = 45;

    private static final Pattern NUMBER = Pattern.compile("[0-9]+");
    private static final String ERROR_MESSAGE_HEADER = "[ERROR] ";
    private static final String NOT_INTEGER_MESSAGE = "숫자가 아닌 값이 입력되었습니다.";
    private static final String NOT_DIVIDED_MESSAGE = "구매값이 1000원으로 나눠지지 않습니다.";

    public void numberValidate(String value) {
        if (isNotConsistOfNumber(value)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_HEADER + NOT_INTEGER_MESSAGE);
        }
    }

    private boolean isNotConsistOfNumber(String value) {
        return !NUMBER.matcher(value).matches();
    }

    public void buyCashValidate(long buyCash) {
        if (isNotDividedByUnit(buyCash)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_HEADER + NOT_DIVIDED_MESSAGE);
        }
        if (isZero(buyCash)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_HEADER + NOT_DIVIDED_MESSAGE);
        }
    }

    private boolean isNotDividedByUnit(long buyCash) {
        return buyCash % BUY_UNIT != 0;
    }

    private boolean isZero(long buyCash) {
        return buyCash == 0;

    }

    public void validateOutOfRange(List<Integer> numbers) {
        List<Integer> outOfRangeNumbers = numbers.stream()
                .filter((number) -> number < NUMBER_LOW_BOUND || number > NUMBER_HIGH_BOUND)
                .toList();

        if (!outOfRangeNumbers.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
