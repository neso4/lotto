package lotto;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.utils.ExceptionMessage;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        validateDuplicate(numbers);
        sortAscend(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    // TODO: 추가 기능 구현
    private void validateDuplicate(List<Integer> numbers) throws IllegalArgumentException {
        Set<Integer> duplicatedNumbers = new HashSet<>(numbers);
        if (duplicatedNumbers.size() < numbers.size()) {
            throw new IllegalArgumentException(ExceptionMessage.ERROR_DUPLICATE_LOTTO_NUMBER);
        }
    }

    private void sortAscend(List<Integer> numbers) {
        Collections.sort(numbers);
    }
}
