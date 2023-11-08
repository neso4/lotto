package lotto.domain;

import lotto.util.LottoUtil;
import lotto.validation.LottoValidation;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = LottoUtil.sortNumbersAscending(numbers);
    }

    private void validate(List<Integer> numbers) {
        LottoValidation.validateRange(numbers);
        LottoValidation.validateDuplicates(numbers);
        LottoValidation.validateLength(numbers);
    }
}
