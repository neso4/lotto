package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {
    private WinningLotto winningLotto;
    @BeforeEach
    void setUp() {
        winningLotto = new WinningLotto();
    }

    @Test
    @DisplayName("로또번호가 1-45사이 숫자가 아니면 예외를 던진다.")
    void 번호는_1과_45_사이() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> winningLotto.saveNumbers(List.of(100, 101, 102, 103, 104, 105)))
                .withMessage("로또 번호는 1과 45 사이여야 합니다.");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> winningLotto.saveBonusNumber(0))
                .withMessage("로또 번호는 1과 45 사이여야 합니다.");
    }

    @Test
    @DisplayName("로또번호가 6개가 아니면 예외를 던진다.")
    void 로또번호가_6개가_아니면_예외() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> winningLotto.saveNumbers(List.of(1, 2, 3, 4, 5, 6, 7)))
                .withMessage("로또 번호의 개수는 6개여야 합니다.");
    }

    @Test
    @DisplayName("로또번호에 중복된 숫자가 있으면 예외를 던진다.")
    void 중복된_숫자_존재시_예외() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> winningLotto.saveNumbers(List.of(1, 2, 3, 4, 5, 5)))
                .withMessage("로또 번호는 서로 중복되지 않은 숫자여야 합니다.");
    }

    @Test
    @DisplayName("보너스 번호가 로또 번호와 중복되면 예외를 던진다.")
    void 중복된_보너스번호일시_예외를() {
        winningLotto.saveNumbers(List.of(1, 2, 3, 4, 5, 6));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> winningLotto.saveBonusNumber(1))
                .withMessage("해당 번호는 보너스 번호로 사용할 수 없습니다. 이미 로또 번호 안에 존재합니다.");
    }
}
