package lotto.model;

import java.util.List;
import lotto.validator.LottoValidator;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers){
        LottoValidator.validateSize(numbers);
        LottoValidator.validateDuplicate(numbers);
        LottoValidator.validateRange(numbers);
    }

}
