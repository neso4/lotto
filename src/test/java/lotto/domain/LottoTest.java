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

    // 아래에 추가 테스트 작성 가능
    @DisplayName("로또 번호에 1~45 범위를 초과하는 값이 오면 예외가 발생한다.")
    @Test
    void createLottoByNormaldNumber() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 48)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 당첨 번호 입력 - 빈 값을 입력한 경우 예외 발생")
    @Test
    void checkInputIsNotEmpty() {
        assertThatThrownBy(() -> new Lotto(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 당첨 번호 입력 - 띄어쓰기만 입력한 경우 예외 발생")
    @Test
    void checkInputIsSpace() {
        assertThatThrownBy(() -> new Lotto("    "))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @DisplayName("(문자열) 로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createStringLottoByOverSize() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto("1,2,3,4,5,6,7"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("(문자열) 로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createStringLottoByDuplicatedNumber() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto("1,2,3,4,5,5"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("(문자열) 로또 번호에 1~45 범위를 초과하는 값이 오면 예외가 발생한다.")
    @Test
    void createStringLottoByNormaldNumber() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto("1,2,3,4,5,55"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("(문자열) 로또 번호에 숫자와 문자가 동시에 들어오면 예외가 발생한다.")
    @Test
    void createStringLottoByChar() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto("1,2,3,4,c,a"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 정상적으로 들어왔을 때 성공")
    @Test
    void createLottoSuccess() {
        Lotto lotto = new Lotto("1,2,3,4,5,6");
        List<Integer> expectedResult = List.of(1, 2, 3, 4, 5, 6);
        assertThat(lotto.getLotto()).isEqualTo(expectedResult);
    }

}