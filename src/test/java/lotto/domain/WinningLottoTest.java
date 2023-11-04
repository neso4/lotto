package lotto.domain;

import static lotto.domain.WinningLotto.NUMBERS_EXCEPTION_MSG;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {
    @DisplayName("당첨 번호 입력시 정수가 아닌 것이 존재하면 예외가 발생한다.")
    @Test
    void inputNotInteger() {
        String numbersInput = ",2.4,5,-,,";
        String bonusInput = "1";
        assertThatThrownBy(() -> new WinningLotto(numbersInput, bonusInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NUMBERS_EXCEPTION_MSG);
    }
}
