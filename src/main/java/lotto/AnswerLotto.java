package lotto;

import constants.ErrorMessage;
import java.util.List;

public class AnswerLotto {
    private final List<Integer> numbers;

    public AnswerLotto(List<Integer> numbers) {
        validateAnswerLotto(numbers);
        this.numbers = numbers;
    }

    private void validateAnswerLotto(List<Integer> numbers) {
        validateLength(numbers);
        validateDuplicateMember(numbers);
        validateAllNumberInRange(numbers);
    }

    private void validateLength(List<Integer> numbers) {
        if (checkLength(numbers)) {
            throw new IllegalArgumentException(ErrorMessage.ANSWER_LOTTO_LENGTH_ERROR.getMessage());
        }
    }

    private boolean checkLength(List<Integer> numbers) {
        return numbers.size() != 6;
    }

    private void validateDuplicateMember(List<Integer> numbers) {
        if (checkHasDuplicateMember(numbers)) {
            throw new IllegalArgumentException(ErrorMessage.ANSWER_LOTTO_HAS_DUPLICATE_NUMBER_ERROR.getMessage());
        }
    }
    private boolean checkHasDuplicateMember(List<Integer> numbers) {
        return numbers.stream()
                .distinct()
                .count() != numbers.size();
    }

    private void validateAllNumberInRange(List<Integer> numbers) {
        if (checkAllNumberInRange(numbers)) {
            throw new IllegalArgumentException(ErrorMessage.ANSWER_LOTTO_NOT_IN_RANGE_ERROR.getMessage());
        }
    }

    private boolean checkAllNumberInRange(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .filter(num -> checkNumberInRange(num))
                .count() == numbers.size();
    }

    private boolean checkNumberInRange(int number) {
        return number >= 1 && number <= 45;
    }
}
