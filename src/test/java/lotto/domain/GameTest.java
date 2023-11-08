package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GameTest {
    @DisplayName("당첨 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createGameByOverSize() {
        assertThatThrownBy(() -> new Game(List.of(1, 2, 3, 4, 5, 6, 7),5))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createGameByDuplicatedNumber() {
        assertThatThrownBy(() -> new Game(List.of(1, 2, 3, 4, 5, 5),6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 미리 입력 받은 6자리 중 포함된다면 예외가 발생한다.")
    @Test
    void validateDuplicateBonusNumber() {
        assertThatThrownBy(() -> new Game(List.of(1, 2, 3, 4, 5, 6),6))
                .isInstanceOf(IllegalArgumentException.class);
    }
}