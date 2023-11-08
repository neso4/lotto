package lotto.domain;

import static org.assertj.core.api.Assertions.*;

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
}
