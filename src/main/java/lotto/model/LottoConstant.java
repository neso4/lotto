package lotto.model;

import java.util.List;

public enum LottoConstant {

    MINIMUM_NUMBER(1),
    MAXIMUM_NUMBER(45),
    LOTTO_NUMBERS_SIZE(6),
    FIRST_PRIZE_MATCH(6),
    SECOND_PRIZE_MATCH(5),
    THIRD_PRIZE_MATCH(5),
    FOURTH_PRIZE_MATCH(4),
    FIFTH_PRIZE_MATCH(3),
    BONUS_MATCH_POINT(50),
    GOAL_MATCH_POINT(100);

    private final int value;

    LottoConstant(final int value) {
        this.value = value;
    }

    public static boolean isNumberValidLottoNumber(final int number) {
        return number >= MINIMUM_NUMBER.getValue() && number <= MAXIMUM_NUMBER.getValue();
    }

    public static void validateIsNumbersValidLottoLength(final List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBERS_SIZE.getValue()) {
            throw new IllegalArgumentException();
        }
    }

    public int getValue() {
        return value;
    }
}
