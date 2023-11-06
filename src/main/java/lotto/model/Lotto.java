package lotto.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }


    public int match(int winnerNumber){
        return Collections.binarySearch(numbers, winnerNumber);
    }
}
