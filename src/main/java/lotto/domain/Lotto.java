package lotto.domain;

import java.util.Collections;
import java.util.List;
import lotto.validator.LottoValidator;

public class Lotto {
    public static final int SIZE = 6;
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        LottoValidator.validate(numbers);
        sort(numbers);
        this.numbers = numbers;
    }

    private void sort(List<Integer> numbers) {
        Collections.sort(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}