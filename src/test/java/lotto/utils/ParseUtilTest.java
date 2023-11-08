package lotto.utils;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class ParseUtilTest {
    @Test
    void parseNumbers() {
        assertThat(ParseUtil.parseNumbers("1,2,3,4,5,6")).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }

    @Test
    void parseNumber() {
        assertThat(ParseUtil.parseNumber("1")).isEqualTo(1);
    }
}
