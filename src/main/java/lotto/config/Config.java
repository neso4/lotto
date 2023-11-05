package lotto.config;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoEnvelop;
import lotto.domain.num.BonusLottoNum;
import lotto.domain.num.LottoTargetNumResults;
import lotto.domain.num.RanNumbers;
import lotto.domain.num.WinLottoNums;
import lotto.domain.user.Judgment;
import lotto.domain.user.RateResult;
import lotto.domain.user.Statistic;
import lotto.domain.user.User;

public class Config {
    public static Lotto lotto() {
        return new Lotto(RanNumbers.createLottoNumbers());
    }

    public static LottoEnvelop lottoEnvelop() {
        return new LottoEnvelop();
    }

    public static BonusLottoNum bonusLottoNum(String strOfPickBonusNum) {
        return new BonusLottoNum(strOfPickBonusNum);
    }

    public static LottoTargetNumResults lottoNumResults(String strOfPickWinNum, String strOfPickBonusNum) {
        return new LottoTargetNumResults(strOfPickWinNum, strOfPickBonusNum);
    }

    public static WinLottoNums winLottoNums(String strOfPickWinNum) {
        return new WinLottoNums(strOfPickWinNum);
    }

    public static Judgment judgment(LottoTargetNumResults lottoTargetNumResults) {
        return new Judgment(lottoTargetNumResults);
    }

    public static RateResult rateResult() {
        return new RateResult();
    }

    public static Statistic statistic(LottoEnvelop lottoEnvelop, LottoTargetNumResults lottoTargetNumResults) {
        return new Statistic(lottoEnvelop, lottoTargetNumResults);
    }

    public static User user(LottoEnvelop lottoEnvelop, LottoTargetNumResults lottoTargetNumResults) {
        return new User(lottoEnvelop, lottoTargetNumResults);
    }
}
