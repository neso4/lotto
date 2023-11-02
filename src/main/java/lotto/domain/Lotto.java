package lotto.domain;

import java.util.List;

import static lotto.exception.ExceptionMessage.LottoException.LOTTO_NUMBER_IS_NOT_IN_RANGE;
import static lotto.exception.ExceptionMessage.LottoException.LOTTO_NUMBER_MUST_BE_UNIQUE;
import static lotto.exception.ExceptionMessage.LottoException.LOTTO_SIZE_IS_NOT_FULFILL;

public class Lotto {
    private static final int TOTAL_SIZE = 6;
    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 45;

    private final List<Integer> numbers;

    private Lotto(final List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static Lotto create(final List<Integer> numbers) {
        validateLottoSize(numbers);
        validateEachNumberIsInRange(numbers);
        validateLottoHasDuplicateNumber(numbers);
        return new Lotto(numbers);
    }

    private static void validateLottoSize(final List<Integer> numbers) {
        if (numbers.size() != TOTAL_SIZE) {
            throw new IllegalArgumentException(LOTTO_SIZE_IS_NOT_FULFILL.message);
        }
    }

    private static void validateEachNumberIsInRange(final List<Integer> numbers) {
        if (hasOutOfRange(numbers)) {
            throw new IllegalArgumentException(LOTTO_NUMBER_IS_NOT_IN_RANGE.message);
        }
    }

    private static boolean hasOutOfRange(final List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(number -> number < LOWER_BOUND || number > UPPER_BOUND);
    }


    private static void validateLottoHasDuplicateNumber(final List<Integer> numbers) {
        if (hasDuplicateNumber(numbers)) {
            throw new IllegalArgumentException(LOTTO_NUMBER_MUST_BE_UNIQUE.message);
        }
    }

    private static boolean hasDuplicateNumber(final List<Integer> number) {
        return number.stream()
                .distinct()
                .count() != TOTAL_SIZE;
    }
}
