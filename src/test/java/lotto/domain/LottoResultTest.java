package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoResultTest {

    @DisplayName("맞춘 숫자의 개수에 따른 결과 확인")
    @Test
    public void testValueOf() {

        assertEquals(LottoResult.NONE, LottoResult.valueOf(2, false));
        assertEquals(LottoResult.THREE, LottoResult.valueOf(3, false));
        assertEquals(LottoResult.FOUR, LottoResult.valueOf(4, false));
        assertEquals(LottoResult.FIVE, LottoResult.valueOf(5, false));
        assertEquals(LottoResult.FIVE_WITH_BONUS, LottoResult.valueOf(5, true));
        assertEquals(LottoResult.SIX, LottoResult.valueOf(6, false));
    }

}