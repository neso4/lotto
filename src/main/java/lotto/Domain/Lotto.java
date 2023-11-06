package lotto.Domain;

import static lotto.CommonValidation.CommonValidation.isValueBetween1And45;
import static lotto.Message.ExceptionMessage.LottoExceptionMessage.*;

import java.util.List;
import lotto.Exception.LottoException;

public class Lotto {
    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        validate(numbers);
        validateDuplicate(numbers);
        for (Integer number : numbers) {
            isValueBetween1And45(number);
        }
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public static Lotto from(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new LottoException(LOTTO_SIZE_ERROR_MESSAGE.getMessage());
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != 6) {
            throw new LottoException(LOTTO_DUPLICATE_ERROR_MESSAGE.getMessage());
        }
    }
}