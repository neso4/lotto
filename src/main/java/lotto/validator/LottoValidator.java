package lotto.validator;

import java.util.List;
import lotto.model.LottoNumber;

public class LottoValidator implements BasicValidator<List<Integer>> {

    private static final String NUMBER_NOT_BETWEEN_ONE_AND_FORTY_FIVE = "[ERROR] 로또 번호는 1과 45 사이여야 합니다.";
    private static final String NUMBER_NOT_SIX_NUMBERS = "[ERROR] 로또 번호는 6자리 이어야 합니다.";
    private static final String NUMBER_DUPLICATED = "[ERROR] 로또 번호는 중복되지 않아야 합니다.";

    @Override
    public void validate(List<Integer> numbers) {
        validateNumbersSize(numbers);
        validateDuplicatedNumber(numbers);
        validateNumbersRange(numbers);
    }

    private void validateNumbersRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            validateNumberRange(number);
        }
    }

    private void validateNumberRange(Integer number) {
        if (!LottoNumber.isInRange(number)) {
            throw new IllegalArgumentException(NUMBER_NOT_BETWEEN_ONE_AND_FORTY_FIVE);
        }
    }

    private void validateNumbersSize(List<Integer> numbers) {
        if (numbers.size() != LottoNumber.COUNT.getNumber()) {
            throw new IllegalArgumentException(NUMBER_NOT_SIX_NUMBERS);
        }
    }

    private void validateDuplicatedNumber(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != LottoNumber.COUNT.getNumber()) {
            throw new IllegalArgumentException(NUMBER_DUPLICATED);
        }
    }
}
