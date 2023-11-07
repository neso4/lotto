package lotto.domain;

import java.util.List;
import lotto.ErrorMessage;

public class Lotto {
    private final List<Integer> numbers;

    public List<Integer> getNumbers() {
        return numbers;
    }

    public Lotto(List<Integer> numbers) throws IllegalArgumentException{
        validate(numbers);
        validateNumberSize(numbers);
        validateRepetition(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_NOT_SIX.getMessage());
        }
    }

    private void validateNumberSize(List<Integer> numbers) {
        int validatedSize = Math.toIntExact(numbers.stream()
                .filter(x -> x <= 45)
                .filter(x -> x >= 1)
                .count());

        if(validatedSize != numbers.size()) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_NUMBER_NUMBER_RANGE.getMessage());
        }
    }

    private void validateRepetition(List<Integer> numbers) {
        int validatedSize = Math.toIntExact(numbers.stream()
                .distinct()
                .count());

        if(validatedSize != numbers.size()) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_REDUNDANT.getMessage());
        }
    }
}
