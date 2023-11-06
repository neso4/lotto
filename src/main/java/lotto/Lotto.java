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
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 6개의 숫자가 아닙니다. 다시 입력해주세요.");
        }

        Set<Integer> numberSet = new HashSet<>(numbers);
        if (numbers.size() != numberSet.size()){
            throw new IllegalArgumentException("[ERROR] 중복된 숫자가 있습니다. 다시 입력해주세요.");
        }

        for (int num: numbers){
            if (!(num >= 1 && num <= 45)){
                throw new IllegalArgumentException("[ERROR] 1에서 45사이의 수가 아닙니다. 다시 입력해주세요.");
            }
        }

        
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }

}
