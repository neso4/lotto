package lotto.domain;


public class BonusLotto {
    private static final int MIN = 1;
    private static final int MAX = 45;
    private static final String ERROR_NUMBER_RANGE = String.format("%d부터 %d까지의 숫자만 입력 가능합니다.", MIN, MAX);
    private int bonusNumber;

    public BonusLotto(int bonusNumber) {
        validate(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    private void validate(int bonusNumber) {
        if (bonusNumber < MIN || bonusNumber > MAX) {
            throw new IllegalArgumentException(ERROR_NUMBER_RANGE);
        }
    }

}
