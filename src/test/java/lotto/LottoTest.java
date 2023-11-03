package lotto;

import lotto.exception.LottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.exception.ErrorMessage.NUMBER_DUPLICATE;
import static lotto.exception.ErrorMessage.NUMBER_MISS;
import static lotto.exception.ErrorMessage.SIZE_MISS;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(SIZE_MISS.getMessage());
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(NUMBER_DUPLICATE.getMessage());
    }

    @DisplayName("로또 번호의 숫자가 규정 크기보다 크거나 작으면 예외가 발생한다.")
    @Test
    void createLottoByMinimumOrMaximumNumber() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 90)))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(NUMBER_MISS.getMessage());
    }
}