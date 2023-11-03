package lotto.domain;

import static lotto.domain.constants.ExceptionMessage.IS_INSUFFICIENT;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(IS_INSUFFICIENT.getMessage());
        }
    }

    // TODO: 추가 기능 구현
}
