package lotto;

import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호의_숫자가_1에서_45_범위가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Lotto.INVALID_RANGE_MESSAGE);
    }

    @Test
    void 올바른_로또_번호() {
        List<Integer> validNumbers = List.of(11, 12, 13, 14, 15, 16);

        Lotto lotto = new Lotto(validNumbers);

        assertEquals(validNumbers, lotto.getNumbers());
    }

    @Test
    void 보너스_번호가_중복되면_예외가_발생한다() {
        Lotto winningNumber = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThatThrownBy(() -> new WinningLotto(winningNumber, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Lotto.DUPLICATE_NUMBER_MESSAGE);
    }

    @Test
    void 올바른_보너스_번호() {
        Lotto winningNumber = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int validBonusNumber = 17;

        WinningLotto winningLotto = new WinningLotto(winningNumber, validBonusNumber);

        assertEquals(validBonusNumber, winningLotto.getBonusNumber());
    }
}