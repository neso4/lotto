package lotto.util.validators;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.util.exception.input.DuplicationNumberException;
import lotto.util.exception.input.LengthGreaterThanSixException;

public class LottoValidator {
    private static final int LOTTO_LENGTH = 6;

    public static void validateLotto(List<Integer> numbers) {
        lengthGreaterThanSix(numbers);
        NumberDuplicates(numbers);
    }

    private static void lengthGreaterThanSix(List<Integer> numbers) {
        if (numbers.size() > LOTTO_LENGTH) {
            throw new LengthGreaterThanSixException();
        }
    }

    private static void NumberDuplicates(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (numbers.size() != uniqueNumbers.size()) {
            throw new DuplicationNumberException();
        }
    }
}
