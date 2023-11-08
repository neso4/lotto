package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;
    private static int PRICE = 1000;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
        if (Set.copyOf(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException();
        }

        for (Integer number : numbers) {
            assertValidNumberRange(number);
        }
    }

    private void assertValidNumberRange(Integer number) {
        if (number > 45 || number < 1) {
            throw new IllegalArgumentException();
        }
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }
    
    public static int getPrice(){
        return Lotto.PRICE;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");

        for (Integer number : numbers) {
            str.append(number).append(", ");
        }

        str.deleteCharAt(str.length()-1);
        str.deleteCharAt(str.length()-1);
        str.append("]");

        return str.toString();
    }
}
