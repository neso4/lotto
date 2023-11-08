package lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import lotto.domain.Lotto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @DisplayName("중복되는 숫자의 개수를 올바르게 반환한다.")
    @Test
    void countDuplicateNumbers() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto comparedLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertEquals(
                lotto.countDuplicateNumbers(comparedLotto), 6
        );

    }

    @DisplayName("올바른 형태로 로또 번호를 출력한다.")
    @Test
    void printLottoNumbers() {
        Lotto lotto = new Lotto(List.of(3, 4, 5, 1, 2, 6));

        Assertions.assertTrue(
                lotto.toString().contains("1, 2, 3, 4, 5, 6")
        );
    }

}