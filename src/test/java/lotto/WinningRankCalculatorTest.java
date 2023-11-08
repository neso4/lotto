package lotto;

import lotto.domain.model.result.WinningRankCalculator;
import lotto.domain.model.lotto.LottoNumber;
import lotto.domain.model.lotto.Lotto;
import lotto.domain.model.lotto.LottoWinningNumbers;
import lotto.domain.model.result.WinningRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinningRankCalculatorTest {

    @DisplayName("로또와 당첨번호, 보너스 번호를 가지고 등수를 결정한다.")
    @Test
    void determineWinningRank() {
        Lotto lotto = Lotto.from(List.of(2, 3, 4, 5, 6, 8));
        Lotto winningNumber = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber lottoNumber = LottoNumber.from(8);
        LottoWinningNumbers lottoWinningNumbers = new LottoWinningNumbers(winningNumber, lottoNumber);
        WinningRankCalculator winningRankCalculator = new WinningRankCalculator();

        //when
        WinningRank actualWinningRank = winningRankCalculator.determineWinningRank(lotto, lottoWinningNumbers);

        //then
        assertThat(actualWinningRank)
                .isEqualTo(WinningRank.SECOND);
    }
}