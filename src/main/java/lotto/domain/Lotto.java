package lotto.domain;

import lotto.exception.DuplicateLottoNumbersException;

import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        isLengthLottoNumber(numbers);
        isDuplicateLottoNumber(numbers);
    }

    private void isLengthLottoNumber(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    private void isDuplicateLottoNumber(List<Integer> number) {
        boolean hasDuplicates = number.size() != new HashSet<>(number).size();

        if (hasDuplicates) {
            throw new DuplicateLottoNumbersException();
        }
    }

    public List<Integer> getNumbers() {
        return this.numbers;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}