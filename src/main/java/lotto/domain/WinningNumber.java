package lotto.domain;

import static lotto.Exception.ExceptionMessage.INVALID_BONUS_NUMBER;

public class WinningNumber {

    private final Lotto winningNumbers;
    private final int bonusNumber;

    public WinningNumber(Lotto lottoNumber, int bonusNumber) {
        validateContainsBonusNumber(lottoNumber, bonusNumber);
        this.winningNumbers = lottoNumber;
        this.bonusNumber = bonusNumber;
    }

    private void validateContainsBonusNumber(Lotto lottoNumber, int bonusNumber) {
        if (lottoNumber.getSortedLottoNumber().contains(bonusNumber)) {
            throw new IllegalArgumentException(INVALID_BONUS_NUMBER.getExceptionMessage());
        }
    }

    public LottoRank getLottoRank(Lotto lotto) {
        int matchCount = getMatchCount(lotto);
        boolean isBonus = containsBonusNumber(lotto);

        return LottoRank.value(matchCount, isBonus);
    }

    public boolean containsBonusNumber(Lotto lotto) {
        return lotto.hasNumber(bonusNumber);
    }

    public int getMatchCount(Lotto lotto) {
        return lotto.getMatchingNumberCount(winningNumbers);
    }

}
