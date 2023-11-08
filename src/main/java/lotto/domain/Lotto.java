package lotto.domain;

import lotto.constant.MagicNumber;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return this.numbers;
    }

    private void validate(List<Integer> numbers) {
        checkNumbersCountSix(numbers);
        checkNumbersUnique(numbers);
        for (Integer number : numbers) {
            checkNumberInRange(number);
        }
    }

    private void checkNumbersCountSix(List<Integer> numbers) {
        if (numbers.size() != MagicNumber.LOTTO_COUNT.getNumber()) {
            throw new IllegalArgumentException("[ERROR] 숫자를 여섯개 입력해 주세요.");
        }
    }

    private void checkNumbersUnique(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (numbers.size() != uniqueNumbers.size()) {
            throw new IllegalArgumentException("[ERROR] 중복이 아닌 숫자를 입력해 주세요.");
        }
    }

    private void checkNumberInRange(int number) {
        if (number < MagicNumber.MIN_NUMBER.getNumber() || number > MagicNumber.MAX_NUMBER.getNumber()) {
            throw new IllegalArgumentException("[ERROR] " + MagicNumber.MIN_NUMBER.getNumber() + " ~ "
                    + MagicNumber.MAX_NUMBER.getNumber() + "사이의 숫자를 입력해 주세요.");
        }
    }
}
