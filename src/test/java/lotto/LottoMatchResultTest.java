package lotto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoMatchResultTest {
    @Test
    void fromMatchedCount() {
        assertThat(LottoMatchResult.fromMatchedCount(0, false)).isEqualTo(LottoMatchResult.UNMATCHED);
        assertThat(LottoMatchResult.fromMatchedCount(3, false)).isEqualTo(LottoMatchResult.MATCHED_3);
        assertThat(LottoMatchResult.fromMatchedCount(4, false)).isEqualTo(LottoMatchResult.MATCHED_4);
        assertThat(LottoMatchResult.fromMatchedCount(5, false)).isEqualTo(LottoMatchResult.MATCHED_5);
        assertThat(LottoMatchResult.fromMatchedCount(5, true)).isEqualTo(LottoMatchResult.MATCHED_5_WITH_BONUS);
        assertThat(LottoMatchResult.fromMatchedCount(6, false)).isEqualTo(LottoMatchResult.MATCHED_6);
    }

    @Test
    void testToString() {
        LottoMatchResult result1 = LottoMatchResult.MATCHED_4;
        assertThat(result1.toString()).isEqualTo("4개 일치 (50,000원)");

        LottoMatchResult result2 = LottoMatchResult.MATCHED_3;
        assertThat(result2.toString()).isEqualTo("3개 일치 (5,000원)");

        LottoMatchResult result3 = LottoMatchResult.MATCHED_5_WITH_BONUS;
        assertThat(result3.toString()).isEqualTo("5개 일치, 보너스 볼 일치 (30,000,000원)");
    }
}