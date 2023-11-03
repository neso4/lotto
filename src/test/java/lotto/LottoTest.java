package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @DisplayName("로또 번호에 지정된 범위를 벗어난 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoWithOutRangedNumber() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 100, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("발행한 로또와 당첨번호를 비교하여 일치하는 숫자의 개수를 반환한다.")
    @Test
    void countHitNumbers() {
        // given
        List<Integer> winningNumbers = List.of(40, 41, 42, 43, 44, 45);
        Lotto lotto0Hit = Lotto.issue(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto1Hit = Lotto.issue(List.of(1, 2, 3, 4, 5, 45));
        Lotto lotto6Hit = Lotto.issue(winningNumbers);
        // when
        Lotto winningLotto = Lotto.issue(winningNumbers);
        // then
        assertThat(lotto0Hit.countHitNumbers(winningLotto)).isEqualTo(0);
        assertThat(lotto1Hit.countHitNumbers(winningLotto)).isEqualTo(1);
        assertThat(lotto6Hit.countHitNumbers(winningLotto)).isEqualTo(6);
    }
}