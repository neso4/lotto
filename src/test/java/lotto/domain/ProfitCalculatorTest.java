package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import lotto.service.PurchaseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitCalculatorTest {
    @Test
    @DisplayName("결과에 따른 수익 퍼센테이지를 정확하게 나타낸다.")
    public void calculateProfitAsPercentage() {
        // given
        ProfitCalculator profitCalculator = new ProfitCalculator();
        Map<Rank, Integer> result = Map.ofEntries(
                Map.entry(Rank.FOURTH, 10),
                Map.entry(Rank.THIRD, 10),
                Map.entry(Rank.NONE, 10),
                Map.entry(Rank.FIFTH, 10)
        );
        // when
        double actualPercentage = profitCalculator.calculateProfitRateInPercentage(result);
        // then
        assertThat(actualPercentage).isEqualByComparingTo(
                (double) (Rank.FOURTH.calculateTotalPrizeMoney(10) +
                        Rank.THIRD.calculateTotalPrizeMoney(10) +
                        Rank.FIFTH.calculateTotalPrizeMoney(10)) / (40 * PurchaseService.LOTTO_PRICE) * 100
        );
    }
}
