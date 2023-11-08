package lotto.Domain;

import lotto.Domain.Lotto;
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

    // 아래에 추가 테스트 작성 가능
    @DisplayName("로또 번호에 1보다 작거나 45보다 큰 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByOutBoundaryNumber() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 1, 2, 3, 4, 46)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호는 오름차순으로 출력된다.")
    @Test
    void printAscendingOrderOfLotto() {
        Lotto lotto = new Lotto(List.of(6, 5, 4, 3, 2, 1));

        Assertions.assertThat(lotto.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }

    @DisplayName("로또 번호에 특정 번호가 있으면 True를 반환한다.")
    @Test
    void returnTrueWhenLottoHasNumber() {
        //given
        Lotto lotto = new Lotto(List.of(1,2,3,4,5,6));

        //when
        int checkNumber = 6;

        //then
        Assertions.assertThat(lotto.hasNumber(checkNumber)).isTrue();
    }
}