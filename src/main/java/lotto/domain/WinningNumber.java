package lotto.domain;

import java.util.List;

public class WinningNumber {
    private final Lotto values;
    private BonusNumber bonusNumber;

    public WinningNumber(List<Integer> values) {
        this.values = new Lotto(values);
    }

    private void validateAlreadyPickedNumber(int bonusNumber) {
        if (values.numbers().contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_DUPLICATE_ERROR.getValue());
        }
    }

    public Lotto getValues() {
        return values;
    }

    public int getBonusNumber() {
        return bonusNumber.value();
    }

    public void setBonusNumber(int bonusNumber) {
        validateAlreadyPickedNumber(bonusNumber);
        this.bonusNumber = new BonusNumber(bonusNumber);
    }
}
