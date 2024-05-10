package lotto.utils;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculationUtilTest {
    @Test
    void toPercentage() {
        assertThat(CalculationUtil.toPercentage(7000L, 5000L)).isEqualTo(71.4);
    }
}
