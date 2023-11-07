package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningNumberTest {

    @DisplayName("당첨 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createWinningNumbersByOverSize() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6, 7), 8))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호와 보너스 번호가 중복되면 예외를 발생한다.")
    @Test
    void createWinningNumbersByDuplicateBonus() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호에 중복된 번호가 있으면 예외가 발생한다.")
    @Test
    void createWinningNumbersByDuplicate() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 5), 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("범위를 벗어난 범위 로또 번호")
    @Test
    void out_of_range() {
        // given
        // when
        // then
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 51), 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
