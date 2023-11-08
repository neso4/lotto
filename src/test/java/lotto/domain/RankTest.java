package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {
    @Test
    void 맞은_숫자개수와_보너스번호_유무를_통해_등수를_계산한다() {
        assertThat(Rank.calcRank(6, true)).isEqualTo(Rank.FIRST);
        assertThat(Rank.calcRank(6, false)).isEqualTo(Rank.FIRST);
        assertThat(Rank.calcRank(5, true)).isEqualTo(Rank.SECOND);
        assertThat(Rank.calcRank(5, false)).isEqualTo(Rank.THIRD);
        assertThat(Rank.calcRank(1, true)).isEqualTo(Rank.LAST);
    }
    @DisplayName("총 상금을 계산한다.")
    @Test
    void 총_상금_계산() {
        assertThat(Rank.calcReward(List.of(Rank.FIRST, Rank.SECOND, Rank.LAST))).isEqualTo(2030000000L);
    }

    @Test
    void toMessage() {
        assertThat(Rank.FIFTH.toMessage()).isEqualTo("3개 일치 (5,000원)");
    }
}
