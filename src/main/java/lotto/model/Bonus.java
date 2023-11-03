package lotto.model;

public class Bonus {

    private final Integer bonusNumber;

    public Bonus(Integer bonusNumber) {
        validate(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validate(Integer bonusNumber) {
        validateRange(bonusNumber);
    }

    private void validateRange(Integer bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException();
        }
    }

    public Boolean hasBonusNumber(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }
}
