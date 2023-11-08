package lotto.domain;

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
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("우승 로또 숫자들과 비교해서 맞은 숫자 개수를 알려준다.")
    @Test
    void 우승_로또_숫자와_비교후_맞은_개수_반환() {
        List<Integer> winnigLottoNumbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto allCorrectLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto allWrongLotto = new Lotto(List.of(7, 8, 9, 10, 11, 12));

        assertThat(allCorrectLotto.calcCorrectNumbers(winnigLottoNumbers)).isEqualTo(6);
        assertThat(allWrongLotto.calcCorrectNumbers(winnigLottoNumbers)).isEqualTo(0);
    }

    @DisplayName("보너스 번호 포함 유무를 알려준다.")
    @Test
    void 보너스_번호_포함_유무_반환() {
        int BONUS_NUMBER = 7;
        Lotto BonusLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto noBonusLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        assertThat(BonusLotto.containsBonusNumber(BONUS_NUMBER)).isFalse();
        assertThat(noBonusLotto.containsBonusNumber(BONUS_NUMBER)).isTrue();
    }
}