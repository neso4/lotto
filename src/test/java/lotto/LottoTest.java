package lotto;

import lotto.domain.Lotto;
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
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 범위 밖의 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByOverRange() {
        assertThatThrownBy(() -> new Lotto(List.of(84, 42, 40, 4, 5, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("현재 로또와 당첨 번호를 비교하여 번호가 같은 개수를 반환한다.")
    @Test
    void compareWithWinningNumbers() {
        Lotto test = new Lotto(List.of(1,2,3,4,5,6));
        Lotto winning = new Lotto(List.of(4,5,6,7,8,9));

        int sameCount = test.countWinningNumber(winning);

        assertThat(sameCount).isEqualTo(3);
    }

    @DisplayName("보너스 번호가 현재 티켓에 포함되어 있는지 체크한다.")
    @Test
    void checkContainsBonusNumber() {
        Lotto test = new Lotto(List.of(1,2,3,4,5,6));
        int bonusNumber = 4;

        boolean isContainBonus = test.contains(bonusNumber);

        assertThat(isContainBonus).isEqualTo(true);
    }
}