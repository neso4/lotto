package lotto;

import lotto.constants.LottoConfig;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (isSizeDifferent(numbers)) {
            throw new IllegalArgumentException();
        }

        if (hasDuplicates(numbers)) {
            throw new IllegalArgumentException();
        }

        if (isNotInRange(numbers)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isSizeDifferent(List<Integer> numbers) {
        return numbers.size() != LottoConfig.LOTTO_NUMBERS_SIZE;
    }

    private boolean hasDuplicates(final List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);

        return numbers.size() != uniqueNumbers.size();
    }

    private boolean isNotInRange(final List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(number -> number < LottoConfig.LOTTO_NUMBER_MIN || number > LottoConfig.LOTTO_NUMBER_MAX);
    }

}
