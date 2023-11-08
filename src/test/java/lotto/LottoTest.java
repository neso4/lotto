package lotto;

import lotto.domain.Lotto;
import org.junit.jupiter.api.Assertions;
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
    @DisplayName("로또 번호가 1~45사이의 값이 아닐 경우 예외가 발생한다.")
    @Test
    void createLottoByNumbersRange() {
        assertThatThrownBy(() -> new Lotto(List.of(46, 1, 0, 2, 3, 4)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또를 생성할때 값이 유효한지 검증한다. 유효하지 않으면 exception을 발생시킨다")
    @Test
    void generateLotto() {
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(
                        () -> new Lotto(List.of(8, 21, 23, 41, 42, 43))),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> new Lotto(List.of(8, 21, 23, 41, 42, 46))),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> new Lotto(List.of(8, 21, 23, 41, 42, 43, 41))),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> new Lotto(List.of(8, 21, 23, 41, 42, 42))),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> new Lotto(List.of(8, 21, 23, 41, 42)))
        );
    }

    @DisplayName("불변 Collection을 반환한다.")
    @Test
    void getLotto(){
        Lotto lotto = new Lotto(List.of(1,2,3,4,5,6));

        List<Integer> copyLotto = lotto.getNumbers();

        Assertions.assertThrows(UnsupportedOperationException.class, ()-> copyLotto.add(1));
    }
}