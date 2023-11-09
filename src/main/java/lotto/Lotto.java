package lotto;

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
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
        Set<Integer> temp = new HashSet<>(numbers);
        if(temp.size() != numbers.size()){
            throw new IllegalArgumentException();
        }
    }


    public List<Integer> getNumbers() { return numbers; }
}
