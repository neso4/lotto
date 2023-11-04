package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.controller.LottoController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 숫자가 아닌 값이 있으면 예외가 발생한다.")
    @Test
    void createLottoByString() {
        LottoController controller = new LottoController();
        assertThatThrownBy(() -> new Lotto(controller.transformInputNumber("1,2,3,4, ,6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자로만 이루어진 값을 입력해주세요.");
    }

    @DisplayName("로또 번호가 1~45 사이가 아니면 예외가 발생한다.")
    @Test
    void createLottoByWrongRange() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 1~45 사이의 숫자를 입력해주세요.");
    }
}