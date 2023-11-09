package lotto.domain.number;

import lotto.config.Config;
import lotto.utill.Utii;

import java.util.List;

public class LottoTargetNumResults {
    private static final Integer BONUS_INDEX_NUM = 6;
    private static final Integer SIZE_WIN_NUMBERS = 6;
    private WinLottoNumbers winLottoNums;
    private BonusLottoNum bonusLottoNum;

    public LottoTargetNumResults(List<Integer> winNumbers, Integer bonusNumber) {
        this.winLottoNums = Config.winLottoNums(winNumbers);
        this.bonusLottoNum = Config.bonusLottoNum(bonusNumber);
    }

    public Boolean isSameWinNums(Integer targetNum) {
        for (int index = 0; index < SIZE_WIN_NUMBERS; index++) {
            if (isContainWinLottoNums(targetNum, index)) {
                return true;
            }
        }

        return false;
    }

    public Boolean isSameBonusNum(Integer targetNum, Integer indexOfSpecial) {
        if (isSameBonusNumber(indexOfSpecial)) {
            return bonusLottoNum.isSame(targetNum);
        }

        return false;
    }

    private Boolean isContainWinLottoNums(Integer targetNum, int index) {
        return winLottoNums.isSameNumOfIndex(targetNum, index);
    }

    private boolean isSameBonusNumber(Integer indexOfSpecial) {
        return Utii.isSameInt(indexOfSpecial, BONUS_INDEX_NUM);
    }

}
