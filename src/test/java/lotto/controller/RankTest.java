package lotto.controller;

import lotto.controller.result.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.controller.result.Rank.getRank;

public class RankTest {
    @DisplayName("맞는 숫자에 따라 맞는 상금을 반환")
    @Test
    void getRankTest() {
        Assertions.assertThat(getRank(6)).isEqualTo(Rank.FIRST);
        //Assertions.assertThat(getRank(5)).isEqualTo(Rank.SECOND);
        Assertions.assertThat(getRank(4)).isNotEqualTo(Rank.THIRD);
        Assertions.assertThat(getRank(4)).isEqualTo(Rank.FOURTH);
        Assertions.assertThat(getRank(3)).isEqualTo(Rank.FIFTH);
    }
}
