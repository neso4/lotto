package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningNumbersTest {
    @DisplayName("당첨 번호와 보너스 번호의 중복을 검증한다.")
    @Test
    void checkWinningAndBonusNumbersDuplication() {
        WinningNumber winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(4);

        assertThatThrownBy(() -> new WinningNumbers(winningNumber, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }
}