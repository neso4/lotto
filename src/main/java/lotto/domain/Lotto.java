package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.constants.Constants;
import lotto.utils.Utils;

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
    public List<Integer> getLottosNumber(){
        return this.numbers;

    }




    // TODO: 추가 기능 구현



}
