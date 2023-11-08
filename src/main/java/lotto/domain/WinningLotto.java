package lotto.domain;

import lotto.enums.ErrorMessages;
import lotto.enums.LottoNumbers;

public class WinningLotto {
    private final Lotto lotto;
    private final Integer bonusNumber;

    public WinningLotto(Lotto lotto, Integer bonusNumber) {
        this.lotto = lotto;
        validate(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validate(Integer bonusNumber) {
        if (isInRange(bonusNumber)) {
            System.out.println(ErrorMessages.IN_RANGE.getDeclaringClass());
            throw new IllegalArgumentException(ErrorMessages.IN_RANGE.getErrorMessage());
        }
        if (lotto.contains(bonusNumber)) {
            System.out.println(ErrorMessages.NOT_DUPLICATE.getErrorMessage());
            throw new IllegalArgumentException(ErrorMessages.NOT_DUPLICATE.getErrorMessage());
        }
    }

    private boolean isInRange(Integer bonusNumber) {
        return bonusNumber < LottoNumbers.MIN_RANGE.getNumber() || bonusNumber > LottoNumbers.MAX_RANGE.getNumber();
    }

    public Integer matchCount(Lotto other) {
        return lotto.matchCount(other);
    }

    public boolean containsBonusNumber(Lotto other) {
        return other.contains(bonusNumber);
    }
}
