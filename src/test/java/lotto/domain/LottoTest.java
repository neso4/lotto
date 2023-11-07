package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @DisplayName("로또의 번호를 정상적으로 입력했을 때 로또 객체가 반환된다.")
    @Test
    void lottoCreateTest() {
        List<Integer> expectedNumbers = List.of(1, 2, 3, 4, 5, 6);

        Lotto lotto = Lotto.of(expectedNumbers);

        assertThat(lotto.getNumbers()).containsExactlyElementsOf(expectedNumbers);
    }

    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> Lotto.of(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호의 개수가 6개 미만으로 되면 예외가 발생한다.")
    @Test
    void createLottoByLessSize() {
        assertThatThrownBy(() -> Lotto.of(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        assertThatThrownBy(() -> Lotto.of(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 정해진 범위를 넘어서면 예외가 발생한다.")
    @Test
    void createLottoByOverRange() {
        assertThatThrownBy(() -> Lotto.of(List.of(1, 2, 3, 4, 46, 5)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Lotto.of(List.of(1, 2, 3, 4, 0, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}