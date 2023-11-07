package lotto.exception;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lotto.exception.ErrorMessage.*;
import static lotto.util.NumberConstant.*;

public class LottoNumberException {
    private LottoNumberException() {
    }

    public static void lottoValidate(List<Integer> numbers) {
        lottoSizeValidate(numbers);
        lottoNumberDuplicateValidate(numbers);
    }

    private static void lottoSizeValidate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(ERROR.getMessage() + LOTTO_SIZE.getMessage());
        }
    }

    private static void lottoNumberDuplicateValidate(List<Integer> numbers) {
        Set<Integer> validate = new HashSet<>(numbers);

        if (validate.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(ERROR.getMessage() + LOTTO_DUPLICATE.getMessage());
        }
    }
}
