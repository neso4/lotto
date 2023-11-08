package lotto.domain;

import java.util.List;
import lotto.utils.ValidationUtil;

public class WinningLotto {
    private List<Integer> numbers;
    private int bonusNumber;

    public Boolean saveNumbers(List<Integer> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers;
        return true;
    }

    public Boolean saveBonusNumber(int bonusNumber) {
        validateBonusNumber(numbers, bonusNumber);
        this.bonusNumber = bonusNumber;
        return true;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    private void validateNumbers(List<Integer> numbers) {
        ValidationUtil.validateHasSixNumbers(numbers);
        numbers.forEach(ValidationUtil::validateNumberInRange);
        ValidationUtil.validateNoDuplicatedNumbers(numbers);
    }

    private void validateBonusNumber(List<Integer> numbers, int bonusNumber) {
        ValidationUtil.validateNumberInRange(bonusNumber);
        ValidationUtil.validateIsBonusNumberDuplicated(numbers, bonusNumber);
    }
}
