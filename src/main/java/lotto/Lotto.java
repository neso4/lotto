package lotto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        Set<Integer> numbersSet = new HashSet<>(numbers);

        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }

        if (numbers.size() != numbersSet.size()) {
            throw new IllegalArgumentException();
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int getWinningCount(List<Integer> winningNumber) {
        List<Integer> matchedNumbers = new ArrayList<>(winningNumber);

        matchedNumbers.retainAll(numbers);
        return matchedNumbers.size();
    }
}
