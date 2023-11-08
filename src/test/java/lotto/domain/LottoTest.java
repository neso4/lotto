package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.dto.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호의 개수가 6개가 아닙니다.");
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 번호가 입력 되었습니다.");
    }

    @DisplayName("로또 번호가 하나라도 범위를 벗어나면 예외가 발생한다.")
    @Test
    void createLottoByNumberOutOfRange() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호 중 범위를 벗어나는 값이 있습니다.");
    }

    @DisplayName("입력된 로또 번호들은 오름차순으로 정렬된다.")
    @Test
    void sortLottoNumbers() {
        //given
        Lotto lotto = new Lotto(List.of(31, 5, 2, 6, 7, 14));
        //when & then
        assertThat(lotto.getNumbers())
                .isEqualTo(List.of(2, 5, 6, 7, 14, 31));
    }
}