package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.constant.LottoConstant;
import lotto.exception.ErrorMessage;
import lotto.validator.ListValidator;
import lotto.validator.NumberValidator;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    private void validate(List<Integer> numbers) {
        ListValidator.of(numbers)
                .shouldHaveSize(LottoConstant.LOTTO_NUMBERS_COUNT, ErrorMessage.WRONG_LOTTO_NUMBERS_COUNT)
                .shouldNotHaveDuplicates(ErrorMessage.LOTTO_NUMBERS_DUPLICATE)
                .shouldAllMatch(this::validateLottoNumberInRange);
    }

    private void validateLottoNumberInRange(int number) {
        NumberValidator.of(number).shouldInRange(
                LottoConstant.LOTTO_NUMBER_MIN,
                LottoConstant.LOTTO_NUMBER_MAX,
                ErrorMessage.LOTTO_NUMBER_OUT_OF_RANGE
        );
    }

    public int match(Lotto lotto) {
        return (int) numbers.stream().filter(lotto::contains).count();
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
