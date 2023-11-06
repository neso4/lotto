package lotto.domain;

import java.util.List;

import static lotto.Constants.LOTTO_COUNT;
import static lotto.exception.ExceptionMessage.DUPLICATED_LOTTO_NUMBER;
import static lotto.exception.ExceptionMessage.INVALID_LOTTO_NUMBER_COUNT;

public class Lotto {

    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream()
                .sorted()
                .map(LottoNumber::new)
                .toList();
    }

    public boolean contains(LottoNumber number){
        return numbers.stream()
                .anyMatch(lottoNumber -> lottoNumber.getNumber() == number.getNumber());
    }

    private void validate(List<Integer> numbers) {
        validateCount(numbers);
        validateDuplicate(numbers);
    }

    private void validateCount(List<Integer> numbers) {
        if (numbers.size() != LOTTO_COUNT) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_COUNT.get(LOTTO_COUNT));
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        long distinctCount = numbers.stream().distinct().count();

        if(numbers.size() != distinctCount) {
            throw new IllegalArgumentException(DUPLICATED_LOTTO_NUMBER.get());
        }
    }
}
