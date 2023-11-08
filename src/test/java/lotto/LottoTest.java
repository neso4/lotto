package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 1부터 45사이의 숫자가 아닌 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByString1() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 47, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @DisplayName("로또 번호에 1부터 45사이의 숫자가 아닌 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByString2() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 45, 5, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @DisplayName("로또 번호에 1부터 45사이의 숫자가 아닌 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByString3() {
        assertThatThrownBy(() -> new Lotto(List.of(1, -6, 3, 4, 5, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @DisplayName("로또 번호의 개수가 6개 미만 이면 예외가 발생한다.")
    @Test
    void createLottoByUnderSize1() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @DisplayName("로또 번호의 개수가 6개가 되지 않으면 예외가 발생한다.")
    @Test
    void createLottoByUnderSize2() {
        assertThatThrownBy(() -> new Lotto(List.of(1)))
                .isInstanceOf(IllegalArgumentException.class);
    }

}