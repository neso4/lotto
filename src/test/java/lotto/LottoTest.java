package lotto;

import lotto.model.Lotto;
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

    @DisplayName("로또 번호에 개수가 5개 이하면 예외가 발생한다.")
    @Test
    void createLottoByUnderSize(){
        assertThatThrownBy(() -> new Lotto(List.of(1,2,3,4,5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호의 max 범위를 벗어나면 예외가 발생한다.")
    @Test
    void createLottoByOverScope(){
        assertThatThrownBy(() -> new Lotto(List.of(1,2,3,4,5,46)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호의 min 범위를 벗어나면 예외가 발생한다.")
    @Test
    void createLottoByUnderScope(){
        assertThatThrownBy(() -> new Lotto(List.of(0,2,3,4,5,6)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}