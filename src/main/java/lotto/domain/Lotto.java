package lotto.domain;

import java.util.List;
import lotto.service.Validate;

public class Lotto {
    private final List<Integer> numbers;
    private final Validate validate = new Validate();

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return this.numbers;
    }

    private void validate(List<Integer> numbers) {
        validate.IsDuplicatedNumber(numbers);
        validate.IsRightNumberLength(numbers);
        validate.IsRightRangeNumber(numbers);
        validate.IsSortedNumbers(numbers);
    }

    // TODO: 추가 기능 구현
}
