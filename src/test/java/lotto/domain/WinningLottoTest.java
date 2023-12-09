package lotto.domain;

import static lotto.global.enums.ErrorMessage.DUPLICATE_NUMBER_ERROR_MESSAGE;
import static lotto.global.enums.ErrorMessage.NUMBER_OUT_OF_RANGE_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import lotto.domain.lotto.Score;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @Test
    @DisplayName("당첨 번호에 보너스 번호가 포함되어 있으면 예외가 발생한다.")
    void createWinningLottoByContainingBonusNumber() {
        assertThatThrownBy(() -> new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DUPLICATE_NUMBER_ERROR_MESSAGE.getMessage());
    }

    @Test
    @DisplayName("보너스 번호가 범위를 벗어나면 예외가 발생한다.")
    void createWinningLottoByOutOfRangeBonusNumber() {
        assertThatThrownBy(() -> new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NUMBER_OUT_OF_RANGE_ERROR_MESSAGE.getMessage());
    }

    @Test
    @DisplayName("당첨 번호와 보너스 번호를 포함 한 객체 생성")
    void createWinningLotto() {
        assertDoesNotThrow(() -> new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7));
    }

    @Test
    @DisplayName("1등 당첨")
    void firstScoreByMatchLotto() {
        //given
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        //when
        Score score = winningLotto.matchLotto(lotto);

        //then
        assertThat(score).isEqualTo(Score.FIRST);
    }

    @Test
    @DisplayName("2등 당첨")
    void secondScoreByMatchLotto() {
        //given
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        //when
        Score score = winningLotto.matchLotto(lotto);

        //then
        assertThat(score).isEqualTo(Score.SECOND);
    }

}