package lotto;

import lotto.domain.Prize;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrizeTest {

    @Test
    void testPrizeAmount() {
        assertEquals(1_000_000_000, Prize.WinningPrize.FIRST_PRIZE.getPrizeAmount());
        assertEquals(50_000_000, Prize.WinningPrize.SECOND_PRIZE.getPrizeAmount());
        assertEquals(1_000_000, Prize.WinningPrize.THIRD_PRIZE.getPrizeAmount());
        assertEquals(50_000, Prize.WinningPrize.FOURTH_PRIZE.getPrizeAmount());
        assertEquals(5_000, Prize.WinningPrize.FIFTH_PRIZE.getPrizeAmount());
    }
}
