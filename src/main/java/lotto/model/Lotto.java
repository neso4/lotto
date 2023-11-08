package lotto.model;

import lotto.exception.model.LottoException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lotto.constant.LottoConfig.*;
import static lotto.view.message.ValidationErrorMessage.*;

public record Lotto(List<Integer> numbers) {
    public Lotto {
        validateCounts(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);
    }

    private void validateCounts(List<Integer> numbers) {
        if (numbers.size() != LOTTO_COUNT_NUMBER.getValue()) {
            throw new LottoException(LOTTO_INVALID_DIGITS.getMessage());
        }
    }

    private void validateRange(List<Integer> numbers) {
        if (numbers.stream()
                .anyMatch(n -> n < LOTTO_START_NUMBER.getValue() || n > LOTTO_END_NUMBER.getValue())) {
            throw new LottoException(LOTTO_NUMBER_OUT_OF_RANGE.getMessage());
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new LottoException(LOTTO_DUPLICATE_DIGITS.getMessage());
        }
    }
}
