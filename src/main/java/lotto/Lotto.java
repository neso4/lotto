package lotto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6 || new HashSet<>(numbers).size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    // TODO: 추가 기능 구현
    // numbers 반환
    public List<Integer> lottoNumbers(){
        return new ArrayList<>(this.numbers);
    }
}
