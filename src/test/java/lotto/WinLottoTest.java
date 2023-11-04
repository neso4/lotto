package lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class WinLottoTest {

    @Test
    void changeNumbersTest() {

    }

    @Test
    void validateSizeTest() {

        assertThatThrownBy(() -> new WinLotto("1,2,3,4,5,6,7", "10"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validateNumTest() {

        assertThatThrownBy(() -> new WinLotto("1,2,3,4,a,6", "10"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validateRangeTest() {

        assertThatThrownBy(() -> new WinLotto("0,2,3,4,5,6", "10"))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new WinLotto("1,2,3,4,46,6", "10"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validateDuplicateTest() {

        assertThatThrownBy(() -> new WinLotto("1,2,3,4,5,4", "10"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validateBonusNumTest() {

        assertThatThrownBy(() -> new WinLotto("1,2,3,4,5,6", "a"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validateBonusNumSpaceTest() {

        assertThatThrownBy(() -> new WinLotto("1,2,3,4,5,6", " "))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validateBonusNumNullTest() {

        assertThatThrownBy(() -> new WinLotto("1,2,3,4,5,6", ""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validateBonusNumDuplicateTest() {

        assertThatThrownBy(() -> new WinLotto("1,2,3,4,5,6", "1"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void compareLottoTest() {

        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinLotto winLotto = new WinLotto("1,2,3,4,5,6", "7");

        int rank = winLotto.compareLotto(lotto);
        assertEquals(6, rank);
    }
}
