package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.utils.NumberConstants;
import lotto.validation.NumberException;

public class Lotto {
    private static final String LEST_WITH_BLANK_DELIMITER = ", ";
    private static final String LOTTO_RESULT_FORMAT = "[%s]";
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        isDuplicate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != NumberConstants.NUMBER_COUNT.getValue()) {
            throw NumberException.INVALID_NUMBER_COUNT_EXCEPTION.getException();
        }
    }

    public int getMatchCount(Lotto otherLotto) {
        return (int) numbers.stream()
            .filter(otherLotto.numbers::contains)
            .count();
    }

    public boolean isContainNumber(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    private void isDuplicate(List<Integer> numbers) {
        boolean result = numbers.stream()
            .anyMatch(winningNumber -> Collections.frequency(numbers, winningNumber) > 1);
        if (result) {
            throw NumberException.DUPLICATE_NUMBER_EXCEPTION.getException();
        }
    }

    @Override
    public String toString() {
        String lottoPrintFormat = numbers.stream()
            .map(Object::toString)
            .collect(Collectors.joining(LEST_WITH_BLANK_DELIMITER));
        return String.format(LOTTO_RESULT_FORMAT, lottoPrintFormat);
    }
}
