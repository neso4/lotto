package lotto.domain;

import java.util.List;

import static lotto.domain.constant.ErrorConst.LOTTO_HAS_DUPLICATE;
import static lotto.domain.constant.ErrorConst.LOTTO_MORE_THAN_COUNT;
import static lotto.domain.constant.ErrorConst.LOTTO_OUT_OF_RANGE;
import static lotto.domain.constant.LottoNumberConst.COUNT;
import static lotto.domain.constant.LottoNumberConst.HIGHEST_NUMBER;
import static lotto.domain.constant.LottoNumberConst.LOWEST_NUMBER;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validate(List<Integer> numbers) {
        validateNumbersRange(numbers);
        validateNumbersCount(numbers);
        validateNumbersUnique(numbers);
    }

    private void validateNumbersRange(List<Integer> numbers) {
        if (numbers.stream().allMatch(
                number -> number >= LOWEST_NUMBER
                        || number <= HIGHEST_NUMBER)) {
            throw new IllegalArgumentException(LOTTO_OUT_OF_RANGE);
        }
    }

    private void validateNumbersCount(List<Integer> numbers) {
        if (numbers.size() != COUNT) {
            throw new IllegalArgumentException(LOTTO_MORE_THAN_COUNT);
        }
    }

    private void validateNumbersUnique(List<Integer> numbers) {
        if (numbers.size() != numbers.stream().distinct().count()) {
            throw new IllegalArgumentException(LOTTO_HAS_DUPLICATE);
        }
    }

    // TODO: 추가 기능 구현
}
