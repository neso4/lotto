package lotto;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import lotto.enums.ErrorMessage;

public class Lotto {
    private static final String OPEN_SQUARE_BRACKETS = "[";
    private static final String CLOSE_SQUARE_BRACKETS = "]";
    private static final int PRICE = 1000;

    private List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
        sort();
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
        validateWinningNumbersNotSame(numbers);
    }

    private static void validateWinningNumbersNotSame(List<Integer> inputs) {
        if (new HashSet<>(inputs).size() != inputs.size()) {
            throw new IllegalArgumentException(ErrorMessage.ERROR_OF_WINNIG_NUMBER_SAME_NUMBER.getMessage());
        }
    }

    private void sort() {
        Collections.sort(numbers);
    }

    public static int getPrice() {
        return PRICE;
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public String toString() {
        String result = numbers.stream().map(String::valueOf).collect(Collectors.joining(", "));
        return OPEN_SQUARE_BRACKETS + result + CLOSE_SQUARE_BRACKETS;
    }

    public int countMatchWithWinningLotto(Lotto winningLotto) {
        int count = 0;
        for (Integer number : numbers) {
            if (winningLotto.contains(number)) {
                count++;
            }
        }

        return count;
    }
}
