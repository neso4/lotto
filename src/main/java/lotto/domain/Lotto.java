package lotto.domain;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        LottoNumberRule rule = LottoNumberRule.BASIC;
        rule.validateLottoNumbers(numbers);
    }

    // TODO: 추가 기능 구현
}
