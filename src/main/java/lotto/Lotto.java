package lotto;

import static lotto.ExceptionHandler.checkWinningNumbersValidity;

import java.util.List;

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
        checkWinningNumbersValidity(numbers);
    }

}
