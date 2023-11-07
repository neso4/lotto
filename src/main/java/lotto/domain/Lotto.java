package lotto.domain;

import java.util.List;

public class Lotto {
    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream()
                .map(LottoNumber::create)
                .toList();
    }

    private void validate(List<Integer> numbers) {
        if (isNotValidLength(numbers)) {
            throw new IllegalArgumentException(String.format(ErrorConstants.INVALID_LENGTH_ERROR_MESSAGE.getMessage(),
                    LOTTO_LENGTH_CONSTRAINT.getValue()));
        }
        if (isDuplicatedNumberExist(numbers)) {
            throw new IllegalArgumentException(ErrorConstants.DUPLICATED_LOTTO_NUMBERS_ERROR_MESSAGE.getMessage());
        }
    }

    // TODO: 추가 기능 구현
    private boolean isNotValidLength(List<Integer> numbers) {
        return numbers.size() != LOTTO_LENGTH_CONSTRAINT.getValue();
    }

    private boolean isDuplicatedNumberExist(List<Integer> numbers) {
        return numbers.stream().distinct().count() != numbers.size();
    }

}
