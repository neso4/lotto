package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;
    public static final int NUMBERS_SIZE = 6;
    public static final int MINIMUM_NUMBER = 1;
    public static final int MAXIMUM_NUMBER = 45;
    public static final int LOTTO_PRICE = 1000;

    public Lotto(List<Integer> numbers) {
        validateNumbers(numbers);

        this.numbers = new ArrayList<>();
        setNumbersInAscendingOrder(numbers);
    }

    private void validateNumbers(List<Integer> numbers) {
        validateNumbersSize(numbers);
        validateNumbersDuplicate(numbers);
    }

    private void validateNumbersSize(List<Integer> numbers) {
        if (numbers.size() != NUMBERS_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    private void validateNumbersDuplicate(List<Integer> numbers) {
        Set<Integer> tmpNumbers = new HashSet<>(numbers);

        if (tmpNumbers.size() != Lotto.NUMBERS_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    private void setNumbersInAscendingOrder(List<Integer> numbers) {
        ArrayList<Integer> tmpNumbers = new ArrayList<>(numbers);
        Collections.sort(tmpNumbers);
        this.numbers.addAll(tmpNumbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public boolean containNumber(int number) {
        return numbers.contains(number);
    }
}
