package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TicketAmountTest {

    @DisplayName("구입 금액이 공백이면 예외가 발생한다.")
    @Test
    void createTicketAmountByIsEmpty() {
        String input = "";

        assertThatThrownBy(() -> new TicketAmount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 숫자가 아닌 경우 예외가 발생한다.")
    @Test
    void createTicketAmountByNotInteger() {
        String input = "10000k";

        assertThatThrownBy(() -> new TicketAmount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 자연수가 아닌 경우 예외가 발생한다.")
    @Test
    void createTicketAmountByNotNatural() {
        String input = "-2000";

        assertThatThrownBy(() -> new TicketAmount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 1,000원으로 나누어 떨어지지 않는 경우 예외가 발생한다.")
    @Test
    void createTicketAmountByNotDivision() {
        String input = "14820";

        assertThatThrownBy(() -> new TicketAmount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액에 맞는 로또 개수를 반환한다.")
    @Test
    void createTicketAmount() {
        String input = "8000";
        TicketAmount ticketAmount = new TicketAmount(input);
        int ticketCount = 8;

        assertEquals(ticketCount, ticketAmount.calculateTicketAmount());
    }

    @DisplayName("수익률을 계산한다.")
    @Test
    void createRateOfReturn() {
        List<LottoRanking> lottoRank = new ArrayList<>();
        lottoRank.add(LottoRanking.FOURTH);
        lottoRank.add(LottoRanking.SECOND);

        String input = "8000";
        TicketAmount ticketAmount = new TicketAmount(input);
        Result lottoResult = new Result(lottoRank);

        double expectedRateOfReturn = (double) lottoResult.calculateTotalPrize()
                / Integer.parseInt(input) * 100;

        assertEquals(expectedRateOfReturn,
                ticketAmount.calculateRateOfReturn(lottoResult));
    }

}
