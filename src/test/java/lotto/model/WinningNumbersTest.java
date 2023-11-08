package lotto.model;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.view.InputView;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WinningNumbersTest {

    @Test
    @DisplayName("당첨 번호가 6개가 아니면 예외처리 한다.")
    void validateWinningNumbersIncorrectCount() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 번호가 6개면 정상 동작한다.")
    void validateWinningNumbersCorrectCount() {
        Lotto WinningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Assertions.assertThat(WinningNumbers.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("당첨 번호가 1 ~ 45 사이의 숫자가 아니면 예외처리한다.")
    void validateRangeOfWinningNumbers() {
        assertThatThrownBy(() -> new Lotto(List.of(10, 55, 42, 33, 22, 11)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void validateByDuplicatedNumber() {
        assertThatThrownBy(() -> new Lotto(List.of(10, 10, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호가 숫자와 쉼표(,)로 이루어져 있는지 검증한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,asdf,4,5,6", "1.2.3.4.5.6"})
    void validateByNumericAndComma(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> InputView.InputWinningNumbers(input));
    }

}
