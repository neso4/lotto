package lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class WinLottoTest {

    @Test
    void changeNumbersTest() {

    }

    @Test
    void validateSizeTest() {

        assertThatThrownBy(() -> new WinLotto("1,2,3,4,5,6,7", "1"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
