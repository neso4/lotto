package lotto;

import static lotto.error.ErrorMessage.NOT_IN_BOUND_LOTTO_NUMBERS;
import static lotto.error.ErrorMessage.NOT_SIX_LOTTO_NUMBERS;
import static lotto.error.ErrorMessage.NOT_UNIQUE_LOTTO_NUMBERS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    private static final int LOTTO_LOWER_BOUND = 1;
    private static final int LOTTO_UPPER_BOUND = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    private final List<Integer> numbers;

    public Lotto(final List<Integer> numbers) {
        validateNumbersCount(numbers);
        validateUniqueNumbers(numbers);
        validateNumbersBound(numbers);

        this.numbers = sort(numbers);
    }

    public static int getLottoLowerBound() {
        return LOTTO_LOWER_BOUND;
    }

    public static int getLottoUpperBound() {
        return LOTTO_UPPER_BOUND;
    }

    public static int getLottoNumberCount() {
        return LOTTO_NUMBER_COUNT;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    private void validateNumbersCount(final List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(NOT_SIX_LOTTO_NUMBERS.getMessage());
        }
    }

    private void validateUniqueNumbers(final List<Integer> numbers) {
        Set<Integer> duplicateRemovedNumbers = new HashSet<>(numbers);

        if (duplicateRemovedNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(NOT_UNIQUE_LOTTO_NUMBERS.getMessage());
        }
    }

    private void validateNumbersBound(final List<Integer> numbers) {
        boolean notInBound = numbers.stream()
                .anyMatch(number -> isLessThanLowerBound(number) || isGreaterThanUpperBound(number));

        if (notInBound) {
            throw new IllegalArgumentException(NOT_IN_BOUND_LOTTO_NUMBERS.getMessage());
        }
    }

    private boolean isGreaterThanUpperBound(Integer number) {
        return number > LOTTO_UPPER_BOUND;
    }

    private boolean isLessThanLowerBound(Integer number) {
        return number < LOTTO_LOWER_BOUND;
    }

    private List<Integer> sort(List<Integer> numbers) {
        List<Integer> sortedNumbers = new ArrayList<>(numbers);

        Collections.sort(sortedNumbers);

        return sortedNumbers;
    }
}
