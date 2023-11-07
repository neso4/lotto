package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
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
    @DisplayName("로또 번호에 1~45 범위 바깥의 숫자가 있으면 예외가 발생한다.")
    @Test
    void 범위_바깥의_숫자로_이루어진_로또_생성() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 45, 46)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("정상값 입력시 로또번호 반환하는지 테스트")
    @Test
    void 로또_번호_반환_테스트() {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(input);
        Assertions.assertThat(lotto.getNumbers())
                .isEqualTo(input);
    }
}