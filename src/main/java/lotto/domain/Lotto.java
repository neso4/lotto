package lotto.domain;

import static lotto.util.Constants.LOTTO_NUMBERS_SIZE;
import static lotto.util.ExceptionMessage.INVALID_LOTTO_SIZE;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.util.LottoUtil;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateDuplication(numbers);
        Collections.sort(numbers);
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(INVALID_LOTTO_SIZE.getMessage());
        }
    }

    private void validateRange(List<Integer> numbers) {
        numbers.forEach(LottoUtil::verifyRangeOfLottoNumber);
    }

    private static void validateDuplication(List<Integer> numbers) {
        if (hasDuplicateNumbers(numbers)) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean hasDuplicateNumbers(List<Integer> numbers) {
        Set<Integer> set = new HashSet<>(numbers);
        return set.size() != numbers.size();
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
