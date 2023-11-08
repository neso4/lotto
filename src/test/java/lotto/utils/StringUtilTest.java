package lotto.utils;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringUtilTest {
    @Test
    void divideByOneThousand() {
        assertThat(StringUtil.divideByOneThousand("2000")).isEqualTo(2);
    }
}