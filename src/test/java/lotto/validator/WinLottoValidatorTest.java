package lotto.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.win.Bonus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinLottoValidatorTest {
    @Test
    @DisplayName("중복된 로또 번호들을 입력했을 때 예외가 발생한다.")
    void ifDuplicationThrowException() {
        //given
        //when
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Bonus bonus = new Bonus(1);

        //then
        assertThatThrownBy(() -> WinLottoValidator.check(lotto, bonus))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
