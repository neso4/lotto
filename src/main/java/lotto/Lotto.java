package lotto;

import lotto.exception.DuplicatedNumberException;
import lotto.exception.InvalidNumbersLengthException;
import lotto.exception.OverRangeWinningNumbersException;
import lotto.message.ExceptionMessage;
import lotto.message.NumberUsedLotto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateRange(numbers);
        validateNumbersLength(numbers);
        validateNumbersDuplicated(numbers);
    }

    private void validateRange(List<Integer> numbers) {
        if (numbers.stream()
                .anyMatch(number -> number > NumberUsedLotto.MAX_LANGE.getNumber()
                        || number < NumberUsedLotto.MIN_RANGE.getNumber())) {
            String message = ExceptionMessage.IS_OVER_RANGE_LOTTO.toString();
            throw new OverRangeWinningNumbersException(message);
        }
    }

    private void validateNumbersDuplicated(List<Integer> numbers) {
        if (numbers.size() != numbers.stream().distinct().count()) {
            String message = ExceptionMessage.IS_DUPLICATED.toString();
            throw new DuplicatedNumberException(message);
        }
    }

    private static void validateNumbersLength(List<Integer> numbers) {
        if (numbers.size() != NumberUsedLotto.NUMBERS_SIZE.getNumber()) {
            String message = ExceptionMessage.IS_NOT_6_LENGTH_OF_LOTTO.toString();
            throw new InvalidNumbersLengthException(message);
        }
    }

    public List<Integer> getSortedNumbers() {
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        sortedNumbers.sort(Comparator.naturalOrder());
        return sortedNumbers;
    }
}
