package lotto.domain;

import java.util.List;
import java.util.Objects;

public class WinningLotto {
    private List<Integer> numbers;
    private int bonusNumber;

    public Boolean saveNumbers(List<Integer> numbers) {
        this.numbers = numbers;
        return true;
    }

    public Boolean saveBonusNumber(int bonusNumber) {
        this.bonusNumber = bonusNumber;
        return true;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
