package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
    
    @DisplayName("로또 번호에 1 ~ 45 범위를 벗어나는 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByOutOfRange() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 88)))
                .isInstanceOf(IllegalArgumentException.class);
    }
    
    @DisplayName("로또 정상적으로 생성.")
    @Test
    void createLottoSuccessfully() {
        List<Integer> numbers = new ArrayList<Integer>(List.of(1, 12, 21, 34, 39, 42));
        Lotto lotto = new Lotto(numbers);
        List<Integer> lottonumbers =  lotto.getNumbers();
        assertThat(lottonumbers.contains(1)).isTrue();
        assertThat(lottonumbers.contains(12)).isTrue();
        assertThat(lottonumbers.contains(21)).isTrue();
        assertThat(lottonumbers.contains(34)).isTrue();
        assertThat(lottonumbers.contains(39)).isTrue();
        assertThat(lottonumbers.contains(42)).isTrue();
    }
}