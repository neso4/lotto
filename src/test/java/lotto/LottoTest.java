package lotto;

import lotto.constant.ExceptionConstant;
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

    // 아래에 추가 테스트 작성 가능
    @DisplayName("로또 번호가 45를 초과할 경우 예외가 발생한다.")
    @Test
    void createLottoOverMaxNum() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 55)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionConstant.LOTTO_NUMBER_SIZE.getMessage());
    }

    @DisplayName("로또 번호가 1 미만일 경우 예외가 발생한다.")
    @Test
    void createLottoDownMinNum() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionConstant.LOTTO_NUMBER_SIZE.getMessage());
    }

    @DisplayName("값이 유효할 경우 객체가 정상 생성된다.")
    @Test
    void createLottoByNormalValue() {
        Lotto lotto = new Lotto(List.of(1, 2, 7, 33, 35, 41));

        assertThat(lotto).isNotNull();
    }

    @DisplayName("동일한 번호의 숫자를 계산하여 출력하는지 확인한다.")
    @Test
    void printSameNumber() {
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 8, 9, 10));

        int sameNumber = lotto1.compareTo(lotto2);
        assertThat(sameNumber).isEqualTo(3);
    }

    @DisplayName("로또 번호와 보너스 번호가 동일하면 true를 반환한다.")
    @Test
    void checkCompareByBonusNumber() {
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 6;
        boolean answer = lotto1.isCompareByBonusNumber(bonusNumber);

        assertThat(answer).isTrue();
    }

    @DisplayName("로또 객체가 불변한지 확인한다. 반환한 객체를 수정하려 할 경우 예외가 발생한다.")
    @Test
    void checkLottoIsImmutable() {
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> lottoNumbers = lotto1.getNumbers();

        assertThatThrownBy(() -> lottoNumbers.add(8))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}