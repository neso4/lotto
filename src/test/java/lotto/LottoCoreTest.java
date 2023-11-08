package lotto;

import static lotto.domain.WinningDetails.calculateResult;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Map;
import lotto.domain.LottoPurchaseInfo;
import lotto.domain.Profit;
import lotto.domain.WinningDetails;
import org.junit.jupiter.api.Test;

class LottoCoreTest {
    @Test
    void 구입금액에_맞는_로또갯수가_생성() {
        LottoPurchaseInfo lottoPurchaseInfo = new LottoPurchaseInfo(5000);
        assertThat(lottoPurchaseInfo.getLottos().size()).isEqualTo(5);
    }

    @Test
    void 다섯가지의_당첨통계_생성() {
        Profit profit = new Profit();
        assertThat(profit.getWinningResult().size()).isEqualTo(5);
    }

    @Test
    void 해당하는_통계_갯수_증가() {
        Profit profit = new Profit();
        profit.addResult(WinningDetails.FOUR_MATCH);
        Map<WinningDetails, Integer> winningMap = profit.getWinningResult();
        assertThat(winningMap.get(WinningDetails.FOUR_MATCH)).isEqualTo(1);
    }

    @Test
    void 소수점_아래_둘째자리에서_반올림() {
        Profit profit = new Profit();
        assertThat(profit.matchDecimalPlace(35.5)).isEqualTo("35.5");
        assertThat(profit.matchDecimalPlace(33.54)).isEqualTo("33.5");
        assertThat(profit.matchDecimalPlace(35.55)).isEqualTo("35.6");
        assertThat(profit.matchDecimalPlace(0.00)).isEqualTo("0.0");
    }

    @Test
    void 로또_일치갯수와_보너스_일치여부에_따라_해당하는_당첨내역_반환() {
        assertThat(calculateResult(3, false)).isEqualTo(WinningDetails.THREE_MATCH);
        assertThat(calculateResult(4, false)).isEqualTo(WinningDetails.FOUR_MATCH);
        assertThat(calculateResult(5, false)).isEqualTo(WinningDetails.FIVE_MATCH);
        assertThat(calculateResult(5, true)).isEqualTo(WinningDetails.FIVE_WITH_BONUS_MATCH);
        assertThat(calculateResult(6, false)).isEqualTo(WinningDetails.SIX_MATCH);
    }


}