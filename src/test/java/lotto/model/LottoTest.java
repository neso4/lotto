package lotto.model;

import lotto.constant.ErrorMessage;
import org.assertj.core.api.Assertions;
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

    @DisplayName("로또 번호가 1~45 범위를 넘어가면 예외 발생")
    @Test
    void 유효하지_않는_범위_번호() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 44, 100, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_IN_RANGE.getMessage());
    }

    @DisplayName("로또 번호 오름차순 정렬 확인")
    @Test
    void 오름차순_정렬_확인(){
        Lotto lotto = new Lotto(List.of(45,16,2,22,14,7));
        Assertions.assertThat(lotto.getNumbers().get(0)).isEqualTo(2);
    }


}