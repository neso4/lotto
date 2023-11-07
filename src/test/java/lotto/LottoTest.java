package lotto;

import lotto.domain.Lotto;
import lotto.exception.lotto.LottoNumberDuplicatedException;
import lotto.exception.lotto.LottoNumberSizeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LottoTest {

    @Test
    void create() {
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        assertNotNull(lotto);
    }

    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> Lotto.from(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(LottoNumberSizeException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        assertThatThrownBy(() -> Lotto.from(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(LottoNumberDuplicatedException.class);
    }
}