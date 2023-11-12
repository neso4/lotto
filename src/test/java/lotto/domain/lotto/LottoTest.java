package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
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
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호 개수 6개 미만이면 예외가 발생한다.")
    @Test
    void createLottoByUnderSize(){
        assertThatThrownBy(() -> new Lotto(List.of(1,2,3,4)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호의 범위(1~45)에 부합하지 않으면 예외가 발생한다.")
    @Test
    void createLottoByInvalidRangeNumber(){
        assertThatThrownBy(() -> new Lotto(List.of(1,2,3,4,5,100)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}