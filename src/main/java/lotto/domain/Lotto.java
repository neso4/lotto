package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;
    public static final int NUMBERS_SIZE = 6;
    public static final int MINIMUM_NUMBER = 1;
    public static final int MAXIMUM_NUMBER = 45;
    public static final int LOTTO_PRICE = 1000;

    public Lotto(List<Integer> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers;
    }

    private void validateNumbers(List<Integer> numbers) {
        validateNumbersSize(numbers);
        validateNumbersDuplicate(numbers);
    }

    private void validateNumbersSize(List<Integer> numbers) {
        if (numbers.size() != NUMBERS_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    private void validateNumbersDuplicate(List<Integer> numbers) {
        Set<Integer> tmpNumbers = new HashSet<>(numbers);

        if (tmpNumbers.size() != Lotto.NUMBERS_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    // TODO: 추가 기능 구현
}
