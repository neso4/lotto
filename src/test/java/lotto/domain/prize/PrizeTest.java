package lotto.domain.prize;

import java.util.List;
import lotto.domain.purchasingMoney.PurchasingMoney;
import lotto.dto.WinningStatisticDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PrizeTest {

    @DisplayName("당첨 여부들과 원금이 주어졌을 때, 수익률을 올바르게 계산한다.")
    @Test
    void calculatePrizeYieldTest() {
        // given
        List<WinningStatisticDTO> winningStatisticDTOs = List.of(
                new WinningStatisticDTO("",1,200),
                new WinningStatisticDTO("",3,600),
                new WinningStatisticDTO("",8,700)
        );
        PurchasingMoney cash = new PurchasingMoney(100000);
        double expectedYield = 7.6;

        // when
        Prize prize = Prize.from(winningStatisticDTOs);
        double yield = prize.getYield(cash);

        // then
        Assertions.assertThat(yield)
                .isEqualTo(expectedYield);
    }

}
