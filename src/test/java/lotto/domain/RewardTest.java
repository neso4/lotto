package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RewardTest {
    @DisplayName("수익률을 소수점 둘째자리수로 반올림하여 반환")
    @Test
    void calcProfitRate() {
        Reward reward = new Reward(5000L);
        assertThat(reward.calcProfitRate(7000L)).isEqualTo(71.4);
    }
}