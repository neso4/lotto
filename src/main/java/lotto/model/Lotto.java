package lotto.model;

import lotto.validator.InputValidator;

import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        InputValidator.validateAmountOfWinningLottoNumber(numbers);
        InputValidator.validateDuplicatedWinningLottoNumber(numbers);
    }

    public int isContainWinningLottoNumber(List<Integer> winningLottoNumbers) {
        int num = 0;
        for (int winningLottoNumber : winningLottoNumbers) {
            num = checkIsContain(num, winningLottoNumber);
        }
        return num;
    }

    private int checkIsContain(int num, int winningLottoNumber) {
        if (numbers.contains(winningLottoNumber)) {
            num++;
        }
        return num;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
