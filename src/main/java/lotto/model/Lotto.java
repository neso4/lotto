package lotto.model;

import lotto.model.constant.LottoConstants;
import lotto.util.validator.InputValidator;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        validateDuplicate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LottoConstants.LOTTO_MAX_COUNT.getNumber()) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        if(numbers.stream().distinct().count() != LottoConstants.LOTTO_MAX_COUNT.getNumber()) {
            throw new IllegalArgumentException();
        }
    }
    // TODO: 추가 기능 구현
}
