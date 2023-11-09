package lotto.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.exception.ErrorMessages;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        validateDuplicate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> numberSet = new HashSet<>(numbers);
        if (numberSet.size() != numbers.size()) {
            throw new IllegalArgumentException(ErrorMessages.DUPLICATE_NUMBER.getMessage());
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
