package lotto.domain;

import java.util.HashSet;
import java.util.List;

public class Lotto {

    private static final String NO_DUPLICATE_ERROR_MESSAGE = "중복된 숫자는 입력할 수 없습니다.";
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }

        if (hasDuplicateNumber(numbers)) {
            throw new IllegalArgumentException(NO_DUPLICATE_ERROR_MESSAGE);
        }
    }

    private boolean hasDuplicateNumber(List<Integer> numbers) {
        return new HashSet<>(numbers).size() != 6;
    }
}
