package lotto.utils;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class StringUtilTest {
    @Test
    void divideByOneThousand() {
        assertThat(StringUtil.divideByOneThousand("2000")).isEqualTo(2);
    }

    @Test
    void splitByCommas() {
        assertThat(StringUtil.splitByCommas("1,2,3,4,5,6")).isEqualTo(List.of("1", "2", "3", "4", "5", "6"));
    }

    @Test
    void divideNumsByCommas() {
        assertThat(StringUtil.divideNumsByCommas(List.of(1, 2, 3, 4, 5, 6))).isEqualTo("1, 2, 3, 4, 5, 6");
    }

    @Test
    void coverWithBrackets() {
        assertThat(StringUtil.coverWithBrackets("1, 2, 3, 4, 5, 6")).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }

    @Test
    void toKoreanWon() {
        assertThat(StringUtil.toKoreanWon(1000L)).isEqualTo("1,000");
    }
}