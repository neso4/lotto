package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RanksTest {

    @DisplayName("등수를 우선순위 정렬해서 저장한다.")
    @Test
    void 우선순위_정렬후_저장() {
        Ranks ranks = new Ranks(List.of(Rank.SECOND, Rank.FIRST));

        assertThat(ranks.getRanks()).isEqualTo(List.of(Rank.FIRST, Rank.SECOND));
    }

    @DisplayName("마지막_등수는_제외하고_반환")
    @Test
    void 마지막_등수는_제외하고_반환() {
        Ranks ranks = new Ranks(List.of(Rank.LAST, Rank.FIRST));

        assertThat(ranks.getRanks()).isEqualTo(List.of(Rank.FIRST));
    }
}
