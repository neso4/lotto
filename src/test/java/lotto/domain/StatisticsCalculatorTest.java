package lotto.domain;

import lotto.domain.dto.Statistics;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsCalculatorTest {

    @Test
    void 수익률이_100_이상일_경우의_최종_결과를_통계로_반환한다() {

        // given
        StatisticsCalculator calculator = new StatisticsCalculator();
        Lottos generatedLottos = new Lottos();
        generatedLottos.addLotto(List.of(1, 2, 3, 4, 5, 6));
        generatedLottos.addLotto(List.of(1, 2, 3, 4, 5, 7));
        Lotto winningNumber = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7, winningNumber);
        Money money = new Money(2000);

        List<Result> expcetedResults = List.of(Result.SIX_MATCH, Result.FIVE_MATCH_WITH_BONUS);
        double expectedRate = 101500000.0;

        // when
        Statistics actual = calculator.calculate(generatedLottos, winningNumber, bonusNumber, money);

        // then
        assertEquals(new Statistics(expcetedResults, expectedRate), actual);
    }
}
