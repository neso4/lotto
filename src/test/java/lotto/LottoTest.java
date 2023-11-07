package lotto;

import lotto.Lottery.Service.Lotto;
import lotto.Lottery.Util.CostChecker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @DisplayName("로또 번호가 1부터 45사이의 값이 아니면 예외가 발생한다.")
    @Test
    void createLottoByOutRangeNumber() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호를 생성할 때에는 반드시 숫자 값을 입력해야 한다.")
    @Test
    void createLottoWithInteger(){
        assertThatThrownBy(() -> new LottoChecker().generateWinner("1,2,eod"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또를 구입할 때에는 반드시 양수 값을 입력해야 한다.")
    @Test
    void createUserMoneyWithPositive(){
        Assertions.assertThat(new CostChecker().isPositive(-1)).isEqualTo(false);
    }

    @DisplayName("로또를 구입할 때에는 반드시 1000원 단위로 값을 입력해야 한다.")
    @Test
    void createUserMoneyWithThousand(){
        Assertions.assertThat(new CostChecker().isDivisionWithThousand(555)).isEqualTo(false);
    }

    @DisplayName("로또를 구입할 때에는 반드시 숫자 값을 입력해야 한다.")
    @Test
    void createUserMoneyWithInteger(){
        assertThatThrownBy(() -> new CostChecker().isNumber("qwer"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호의 입력 값은 반드시 숫자여야 한다.")
    @Test
    void createBonusNumberWithInteger(){
        assertThatThrownBy(() -> new BonusChecker().isNumber("qwer"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호의 값은 반드시 1부터 45까지의 숫자로 입력되어야 한다.")
    @Test
    void createBonusNumberInRange(){
        Assertions.assertThat(new BonusChecker().isInRange(50)).isEqualTo(false);
    }

    @DisplayName("보너스 번호의 값이 당첨번호와 중복되면 오류가 발생한다.")
    @Test
    void BonusNumberIsInTheWinners() {
        Assertions.assertThat(new Lotto(List.of(1, 2, 3, 4, 5, 6))
                .bonusValidate(3)).isEqualTo(false);
    }
}